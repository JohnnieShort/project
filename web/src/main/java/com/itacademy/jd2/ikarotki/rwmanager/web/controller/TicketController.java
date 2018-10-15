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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TicketToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TicketDTO;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {
	private ITicketService ticketService;
	TicketToDTOConverter toDtoConverter;

	@Autowired
	private TicketController(ITicketService ticketService, TicketToDTOConverter toDtoConverter) {
		super();
		this.ticketService = ticketService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<ITicket> entities = ticketService.getAll();
		List<TicketDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("ticket.list", models);
	}
}
