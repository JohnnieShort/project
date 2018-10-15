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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CargoRouteToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoRouteDTO;

@Controller
@RequestMapping(value = "/cargoRoute")
public class CargoRouteController {
	private ICargoRouteService cargoRouteService;
	CargoRouteToDTOConverter toDtoConverter;

	@Autowired
	private CargoRouteController(ICargoRouteService cargoRouteService, CargoRouteToDTOConverter toDtoConverter) {
		super();
		this.cargoRouteService = cargoRouteService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<ICargoRoute> entities = cargoRouteService.getAll();
		List<CargoRouteDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("cargoRoute.list", models);
	}
}
