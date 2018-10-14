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

		dto.setPassengerRoute(entity.getPassengerRoute());
		dto.setDeparture(entity.getDeparture());
		dto.setArrival(entity.getArrival());
		dto.setStationFrom(entity.getStationFrom());
		dto.setStationTo(entity.getStationTo());

		dto.setOrdinalNum(entity.getOrdinalNum());
		dto.setIsFirst(entity.getIsFirst());
		dto.setIsLast(entity.getIsLast());

		return dto;
	}
}
