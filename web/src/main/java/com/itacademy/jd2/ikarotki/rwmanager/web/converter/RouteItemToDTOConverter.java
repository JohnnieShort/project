package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RouteItemDTO;

@Component
public class RouteItemToDTOConverter implements Function<IRouteItem, RouteItemDTO> {
	@Override
	public RouteItemDTO apply(final IRouteItem entity) {
		final RouteItemDTO dto = new RouteItemDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		dto.setPassengerRouteId(entity.getPassengerRoute().getId());
		dto.setDepartureTime(entity.getDeparture());
		dto.setDepartureDate(entity.getDeparture());
		dto.setArrivalTime(entity.getArrival());
		dto.setArrivalDate(entity.getArrival());
		dto.setStationFromId(entity.getStationFrom().getId());
		dto.setStationFromName(entity.getStationFrom().getName());

		dto.setStationToId(entity.getStationTo().getId());
		dto.setStationToName(entity.getStationTo().getName());

		dto.setOrdinalNum(entity.getOrdinalNum());

		return dto;
	}
}
