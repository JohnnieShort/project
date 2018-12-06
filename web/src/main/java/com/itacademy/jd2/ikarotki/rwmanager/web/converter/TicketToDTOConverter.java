package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TicketDTO;

@Component
public class TicketToDTOConverter implements Function<ITicket, TicketDTO> {
	@Override
	public TicketDTO apply(final ITicket entity) {
		final TicketDTO dto = new TicketDTO();

		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		dto.setStationFromId(entity.getStationFrom().getId());
		dto.setStationToId(entity.getStationTo().getId());

		dto.setPrice(entity.getPrice());
		dto.setPassengerId(entity.getPassenger().getId());
		dto.setPassengerRouteId(entity.getPassengerRoute().getId());

		return dto;
	}
}
