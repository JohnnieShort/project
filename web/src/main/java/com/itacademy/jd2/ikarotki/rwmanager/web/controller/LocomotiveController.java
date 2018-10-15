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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.service.ILocomotiveService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.LocomotiveToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.LocomotiveDTO;

@Controller
@RequestMapping(value = "/locomotive")
public class LocomotiveController {
	private ILocomotiveService locomotiveService;
	LocomotiveToDTOConverter toDtoConverter;

	@Autowired
	private LocomotiveController(ILocomotiveService locomotiveService, LocomotiveToDTOConverter toDtoConverter) {
		super();
		this.locomotiveService = locomotiveService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<ILocomotive> entities = locomotiveService.getAll();
		List<LocomotiveDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("locomotive.list", models);
	}
}
