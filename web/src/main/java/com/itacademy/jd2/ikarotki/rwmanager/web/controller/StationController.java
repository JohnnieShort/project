package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.StationFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.StationFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.StationToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.StationDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/station")
public class StationController extends AbstractController<StationDTO> {
	private IStationService stationService;
	private StationToDTOConverter toDtoConverter;
	private StationFromDTOConverter fromDtoConverter;

	@Autowired
	private StationController(IStationService stationService, StationToDTOConverter toDtoConverter,
			StationFromDTOConverter fromDtoConverter) {
		super();
		this.stationService = stationService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO  gridState = getListDTO(req);
		gridState.setSort(sortColumn, "id");
		gridState.setPage(pageNumber);

		final StationFilter filter = new StationFilter();
		prepareFilter(gridState, filter);

		final List<IStation> entities = stationService.find(filter);
		List<StationDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(stationService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("station.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IStation newEntity = stationService.createEntity();
		StationDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("station.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final StationDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "station.edit";
		} else {
			final IStation entity = fromDtoConverter.apply(formModel);
			stationService.save(entity);
			return "redirect:/station";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		stationService.delete(id);
		return "redirect:/station";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IStation dbModel = stationService.get(id);
		final StationDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("station.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final StationDTO dto = toDtoConverter.apply(stationService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("station.edit", hashMap);
	}

}
