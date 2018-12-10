package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TicketDTO;

@Component
public class TicketFromDTOConverter implements Function<TicketDTO, ITicket> {
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IPassengerService passengerService;
	@Autowired
	private IPassengerRouteService passengerRouteService;
	@Autowired
	private IStationService stationService;

	@Override
	public ITicket apply(final TicketDTO dto) {
		final ITicket entity = ticketService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		entity.setPrice(dto.getPrice());
		
		if(dto.getPassengerId()!=null) {
			IPassenger passenger = passengerService.createEntity();
			passenger.setId(dto.getPassengerId());
			entity.setPassenger(passenger);
		}
		if(dto.getPassengerRouteId()!=null) {
			IPassengerRoute passengerRoute = passengerRouteService.createEntity();
			passengerRoute.setId(dto.getPassengerRouteId());
			entity.setPassengerRoute(passengerRoute);
		}
		if(dto.getStationFromId()!=null) {
			IStation stationFrom = stationService.createEntity();
			stationFrom.setId(dto.getStationFromId());
			entity.setStationFrom(stationFrom);
		}
		if(dto.getStationToId()!=null) {
			IStation stationTo = stationService.createEntity();
			stationTo.setId(dto.getStationToId());
			entity.setStationTo(stationTo);
		}
		

		return entity;
	}
}
