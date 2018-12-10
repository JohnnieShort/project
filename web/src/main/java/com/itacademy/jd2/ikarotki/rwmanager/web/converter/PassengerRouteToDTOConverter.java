package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;

@Component
public class PassengerRouteToDTOConverter implements Function<IPassengerRoute, PassengerRouteDTO> {
	private IRouteItemService itemService;
	@Autowired
	private PassengerRouteToDTOConverter(IRouteItemService itemService) {
		this.itemService = itemService;
	}
	@Override
	public PassengerRouteDTO apply(final IPassengerRoute entity) {
		final PassengerRouteDTO dto = new PassengerRouteDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setVersion(entity.getVersion());
		
		RouteItemFilter filter = new RouteItemFilter();
		filter.setFetchStationFrom(true);
		filter.setFetchStationTo(true);
		dto.setName(itemService.getRouteName(entity.getId(), filter));

		

		dto.setPassengerRouteType(entity.getPassengerRouteType().name());
		if(entity.getTrain()!=null){
		dto.setTrainId(entity.getTrain().getId());
		}
		dto.setIsActual(entity.getIsActual());
		dto.setFrequency(entity.getFrequency().name());
		

		return dto;
	}
}
