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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerRouteFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerRouteToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/passengerRoute")
public class PassengerRouteController extends AbstractController<PassengerRouteDTO> {
	private IPassengerRouteService passengerRouteService;
	private PassengerRouteToDTOConverter toDtoConverter;
	private PassengerRouteFromDTOConverter fromDtoConverter;

	@Autowired
	private PassengerRouteController(IPassengerRouteService pasengerRouteService,
			PassengerRouteToDTOConverter toDtoConverter, PassengerRouteFromDTOConverter fromDtoConverter) {
		super();
		this.passengerRouteService = pasengerRouteService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false, defaultValue = "id") final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PassengerRouteFilter filter = new PassengerRouteFilter();
		prepareFilter(gridState, filter);

		final List<IPassengerRoute> entities = passengerRouteService.find(filter);
		List<PassengerRouteDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(passengerRouteService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("passengerRoute.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IPassengerRoute newEntity = passengerRouteService.createEntity();
		PassengerRouteDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("passengerRoute.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final PassengerRouteDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "passengerRoute.edit";
		} else {
			final IPassengerRoute entity = fromDtoConverter.apply(formModel);
			passengerRouteService.save(entity);
			return "redirect:/passengerRoute";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		passengerRouteService.delete(id);
		return "redirect:/passengerRoute";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPassengerRoute dbModel = passengerRouteService.get(id);
		final PassengerRouteDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("passengerRoute.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PassengerRouteDTO dto = toDtoConverter.apply(passengerRouteService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("passengerRoute.edit", hashMap);
	}

}
