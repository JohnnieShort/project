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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerRouteToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;

@Controller
@RequestMapping(value = "/passengerRoute")
public class PassengerRouteController {
	private IPassengerRouteService passengerRouteService;
	PassengerRouteToDTOConverter toDtoConverter;

	@Autowired
	private PassengerRouteController(IPassengerRouteService pasengerRouteService,
			PassengerRouteToDTOConverter toDtoConverter) {
		super();
		this.passengerRouteService = pasengerRouteService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<IPassengerRoute> entities = passengerRouteService.getAll();
		List<PassengerRouteDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("passengerRoute.list", models);
	}
}
