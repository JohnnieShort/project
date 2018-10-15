package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.RouteItemToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RouteItemDTO;

@Controller
@RequestMapping(value = "/routeItem")
public class RouteItemController {
	private IRouteItemService routeItemService;
	RouteItemToDTOConverter toDtoConverter;

	@Autowired
	private RouteItemController(IRouteItemService routeItemService, RouteItemToDTOConverter toDtoConverter) {
		super();
		this.routeItemService = routeItemService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<IRouteItem> entities = routeItemService.getAll();
		List<RouteItemDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("routeItem.list", models);
	}
}
