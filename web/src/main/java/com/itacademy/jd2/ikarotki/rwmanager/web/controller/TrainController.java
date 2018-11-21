package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.TrainType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.LocomotiveFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ILocomotiveService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.LocomotiveToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TrainFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TrainToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TrainDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/train")
public class TrainController extends AbstractController<TrainDTO> {

	private ITrainService trainService;
	private ILocomotiveService locomotiveService;
	private TrainToDTOConverter toDtoConverter;
	private TrainFromDTOConverter fromDtoConverter;

	@Autowired
	private TrainController(ITrainService trainService, TrainToDTOConverter toDtoConverter,
			TrainFromDTOConverter fromDtoConverter, ILocomotiveService locomotiveService,
			LocomotiveToDTOConverter locToDtoConverter) {
		super();
		this.trainService = trainService;
		this.locomotiveService = locomotiveService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final TrainFilter filter = new TrainFilter();
		filter.setFetchLocomotive(true);
		prepareFilter(gridState, filter);

		final List<ITrain> entities = trainService.find(filter);
		List<TrainDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(trainService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("train.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		// final ITrain newEntity = trainService.createEntity();
		TrainDTO dto = new TrainDTO();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("train.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final TrainDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("train.edit", hashMap);
		} else {
			final ITrain entity = fromDtoConverter.apply(formModel);
			trainService.save(entity);
			return "redirect:/train";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		trainService.delete(id);
		return "redirect:/train";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITrain dbModel = trainService.getFullInfo(id);
		final TrainDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("train.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TrainDTO dto = toDtoConverter.apply(trainService.getFullInfo(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);

		return new ModelAndView("train.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {

		final Map<Integer, String> locomotives = locomotiveService.find(new LocomotiveFilter()).stream()
				.collect(Collectors.toMap(ILocomotive::getId, ILocomotive::getName));
		List<TrainType> trainTypes = Arrays.asList(TrainType.values());
		hashMap.put("trainTypes", trainTypes);
		hashMap.put("modelsLocomotive", locomotives);

	}
}
