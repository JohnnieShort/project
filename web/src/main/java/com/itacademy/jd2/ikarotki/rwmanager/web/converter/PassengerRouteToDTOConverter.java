package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;

@Component
public class PassengerRouteToDTOConverter implements Function<IPassengerRoute, PassengerRouteDTO> {
	@Override
	public PassengerRouteDTO apply(final IPassengerRoute entity) {
		final PassengerRouteDTO dto = new PassengerRouteDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setVersion(entity.getVersion());
		

		

		dto.setPassengerRouteType(entity.getPassengerRouteType().name());
		if(entity.getTrain()!=null){
		dto.setTrainId(entity.getTrain().getId());
		}
		dto.setIsActual(entity.getIsActual());
		dto.setFrequency(entity.getFrequency().name());
		

		return dto;
	}
}
