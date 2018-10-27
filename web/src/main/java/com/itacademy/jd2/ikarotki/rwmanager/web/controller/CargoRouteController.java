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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CargoRouteFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CargoRouteToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoRouteDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/cargoRoute")
public class CargoRouteController extends AbstractController<CargoRouteDTO> {
	private ICargoRouteService cargoRouteService;
	private CargoRouteToDTOConverter toDtoConverter;
	private CargoRouteFromDTOConverter fromDtoConverter;

	@Autowired
	private CargoRouteController(ICargoRouteService cargoRouteService, CargoRouteToDTOConverter toDtoConverter,
			CargoRouteFromDTOConverter fromDtoConverter) {
		super();
		this.cargoRouteService = cargoRouteService;
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

		final CargoRouteFilter filter = new CargoRouteFilter();
		prepareFilter(gridState, filter);

		final List<ICargoRoute> entities = cargoRouteService.find(filter);
		List<CargoRouteDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(cargoRouteService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("cargoRoute.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICargoRoute newEntity = cargoRouteService.createEntity();
		CargoRouteDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("cargoRoute.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final CargoRouteDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "cargoRoute.edit";
		} else {
			final ICargoRoute entity = fromDtoConverter.apply(formModel);
			cargoRouteService.save(entity);
			return "redirect:/cargoRoute";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		cargoRouteService.delete(id);
		return "redirect:/cargoRoute";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICargoRoute dbModel = cargoRouteService.get(id);
		final CargoRouteDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("cargoRoute.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CargoRouteDTO dto = toDtoConverter.apply(cargoRouteService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("cargoRoute.edit", hashMap);
	}

}
