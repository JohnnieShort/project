package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RouteItemDTO;

@Component
public class RouteItemFromDTOConverter implements Function<RouteItemDTO, IRouteItem> {
	@Autowired
	private IRouteItemService routeItemService;
	@Autowired
	private IPassengerRouteService passengerRouteService;
	@Autowired
	private IStationService stationService;

	@Override
	public IRouteItem apply(final RouteItemDTO dto) {
		final IRouteItem entity = routeItemService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		IPassengerRoute pREntity = passengerRouteService.createEntity();
		pREntity.setId(dto.getPassengerRouteId());
		entity.setPassengerRoute(pREntity);
		entity.setDeparture(dto.getDeparture());
		entity.setArrival(dto.getArrival());

		IStation stationFrom = stationService.createEntity();
		stationFrom.setId(dto.getStationFromId());
		if (dto.getStationFromName() != null) {
			stationFrom.setName(dto.getStationFromName());
		}
		entity.setStationFrom(stationFrom);

		IStation stationTo = stationService.createEntity();
		stationTo.setId(dto.getStationToId());
		if (dto.getStationToName() != null) {
			stationTo.setName(dto.getStationToName());
		}
		entity.setStationTo(stationTo);

		entity.setOrdinalNum(dto.getOrdinalNum());

		return entity;
	}
}
