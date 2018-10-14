package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TicketDTO;

@Component
public class TicketFromDTOConverter implements Function<TicketDTO, ITicket> {
	@Autowired
	private ITicketService ticketService;

	@Override
	public ITicket apply(final TicketDTO dto) {
		final ITicket entity = ticketService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		entity.setPrice(dto.getPrice());
		entity.setPassenger(dto.getPassenger());
		entity.setPassengerRoute(dto.getPassengerRoute());
		entity.setTo(dto.getTo());
		entity.setFrom(dto.getFrom());

		return entity;
	}
}
