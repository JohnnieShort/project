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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TrainToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TrainDTO;

@Controller
@RequestMapping(value = "/train")
public class TrainController {

	private ITrainService trainService;
	TrainToDTOConverter toDtoConverter;

	@Autowired
	private TrainController(ITrainService trainService, TrainToDTOConverter toDtoConverter) {
		super();
		this.trainService = trainService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<ITrain> entities = trainService.getAll();
		List<TrainDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("train.list", models);
	}
}
