package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RouteItemDTO;

@Component
public class RouteItemFromDTOConverter implements Function<RouteItemDTO, IRouteItem> {
	@Autowired
	private IRouteItemService routeItemService;

	@Override
	public IRouteItem apply(final RouteItemDTO dto) {
		final IRouteItem entity = routeItemService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		entity.setPassengerRoute(dto.getPassengerRoute());
		entity.setDeparture(dto.getDeparture());
		entity.setArrival(dto.getArrival());
		entity.setStationFrom(dto.getStationFrom());
		entity.setStationTo(dto.getStationTo());

		entity.setOrdinalNum(dto.getOrdinalNum());
		entity.setIsFirst(dto.getIsFirst());
		entity.setIsLast(dto.getIsLast());

		return entity;
	}
}
