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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IWagonService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TrainToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.WagonFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.WagonToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.WagonDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/wagon")
public class WagonController extends AbstractController<WagonDTO> {
	private IWagonService wagonService;
	private ITrainService trainService;
	private TrainToDTOConverter trainToDTOConverter;
	private WagonToDTOConverter toDtoConverter;
	private WagonFromDTOConverter fromDtoConverter;

	@Autowired
	private WagonController(IWagonService wagonService, WagonToDTOConverter toDtoConverter,
			WagonFromDTOConverter fromDtoConverter, ITrainService trainService,
			TrainToDTOConverter trainToDTOConverter) {
		super();
		this.wagonService = wagonService;
		this.trainService = trainService;
		this.trainToDTOConverter = trainToDTOConverter;
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

		final WagonFilter filter = new WagonFilter();
		prepareFilter(gridState, filter);

		final List<IWagon> entities = wagonService.find(filter);
		List<WagonDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(wagonService.getCount(filter));

		final HashMap<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("wagon.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		WagonDTO dto = new WagonDTO();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);

		return new ModelAndView("wagon.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final WagonDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("wagon.edit", hashMap);
		} else {
			final IWagon entity = fromDtoConverter.apply(formModel);
			wagonService.save(entity);
			return "redirect:/wagon";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		wagonService.delete(id);
		return "redirect:/wagon";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IWagon dbModel = wagonService.getFullInfo(id);
		final WagonDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);

		return new ModelAndView("wagon.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final WagonDTO dto = toDtoConverter.apply(wagonService.getFullInfo(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);

		return new ModelAndView("wagon.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		TrainFilter filter = new TrainFilter();
		filter.setFetchLocomotive(true);
		final List<ITrain> trains = trainService.find(new TrainFilter());
		//final List<TrainDTO> trainsDto = trains.stream().map(trainToDTOConverter).collect(Collectors.toList());
		List<WagonType> wagonTypes = Arrays.asList(WagonType.values());
		hashMap.put("wagonTypes", wagonTypes);

		final Map<Integer, String> trainChoices = trains.stream().collect(Collectors.toMap(ITrain::getId, train -> {
			return train.getId()+" "+ train.getTrainType().name();
		}));

		hashMap.put("trainsChoices", trainChoices);

	}
}
