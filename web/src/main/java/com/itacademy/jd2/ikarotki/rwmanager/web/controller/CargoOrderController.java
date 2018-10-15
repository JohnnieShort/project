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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoOrderService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CargoOrderToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoOrderDTO;

@Controller
@RequestMapping(value = "/cargoOrder")
public class CargoOrderController {
	private ICargoOrderService cargoOrderService;
	CargoOrderToDTOConverter toDtoConverter;

	@Autowired
	private CargoOrderController(ICargoOrderService cargoOrderService, CargoOrderToDTOConverter toDtoConverter) {
		super();
		this.cargoOrderService = cargoOrderService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<ICargoOrder> entities = cargoOrderService.getAll();
		List<CargoOrderDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("cargoOrder.list", models);
	}
}
