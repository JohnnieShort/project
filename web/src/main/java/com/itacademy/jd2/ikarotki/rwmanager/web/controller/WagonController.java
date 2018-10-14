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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.service.IWagonService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.WagonToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.WagonDTO;

@Controller
@RequestMapping(value = "/wagon")
public class WagonController {
	private IWagonService wagonService;
	WagonToDTOConverter toDtoConverter;

	@Autowired
	private WagonController(IWagonService wagonService, WagonToDTOConverter toDtoConverter) {
		super();
		this.wagonService = wagonService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<IWagon> entities = wagonService.getAll();
		List<WagonDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("wagon.list", models);
	}
}
