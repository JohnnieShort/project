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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.RouteItemFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.RouteItemToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RouteItemDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/routeItem")
public class RouteItemController extends AbstractController<RouteItemDTO> {
	private IRouteItemService routeItemService;
	private RouteItemToDTOConverter toDtoConverter;
	private RouteItemFromDTOConverter fromDtoConverter;

	@Autowired
	private RouteItemController(IRouteItemService routeItemService, RouteItemToDTOConverter toDtoConverter,
			RouteItemFromDTOConverter fromDtoConverter) {
		super();
		this.routeItemService = routeItemService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "sort", required = false, defaultValue = "id") final String sortColumn) {

		final GridStateDTO listDTO = getListDTO(req);
		listDTO.setSort(sortColumn, "id");

		final RouteItemFilter filter = new RouteItemFilter();
		prepareFilter(listDTO, filter);

		final List<IRouteItem> entities = routeItemService.find(filter);

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", entities.stream().map(toDtoConverter).collect(Collectors.toList()));
		return new ModelAndView("routeItem.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IRouteItem newEntity = routeItemService.createEntity();
		RouteItemDTO dto = toDtoConverter.apply(newEntity);
		hashMap.put("formModel", dto);

		return new ModelAndView("routeItem.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final RouteItemDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "routeItem.edit";
		} else {
			final IRouteItem entity = fromDtoConverter.apply(formModel);
			routeItemService.save(entity);
			return "redirect:/routeItem";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		routeItemService.delete(id);
		return "redirect:/routeItem";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IRouteItem dbModel = routeItemService.get(id);
		final RouteItemDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("routeItem.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final RouteItemDTO dto = toDtoConverter.apply(routeItemService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("routeItem.edit", hashMap);
	}

}
