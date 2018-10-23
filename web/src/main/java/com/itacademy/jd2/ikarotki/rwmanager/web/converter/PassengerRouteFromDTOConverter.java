package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;

@Component
public class PassengerRouteFromDTOConverter implements Function<PassengerRouteDTO, IPassengerRoute> {
	@Autowired
	private IPassengerRouteService passengerRouteService;

	@Override
	public IPassengerRoute apply(final PassengerRouteDTO dto) {
		final IPassengerRoute entity = passengerRouteService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		entity.setStationFrom(dto.getStationFrom());
		entity.setStationTo(dto.getStationTo());

		entity.setDeparture(dto.getDeparture());
		entity.setArrival(dto.getArrival());

		entity.setPassengerRoutetype(dto.getPassengerRoutetype());
		entity.setTrain(dto.getTrain());
		entity.setIsActual(dto.getIsActual());
		entity.setFrequency(dto.getFrequency());
		entity.setPlaces(dto.getPlaces());

		return entity;
	}
}
