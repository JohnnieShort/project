package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoRouteDTO;

@Component
public class CargoRouteToDTOConverter implements Function<ICargoRoute, CargoRouteDTO> {
	@Override
	public CargoRouteDTO apply(final ICargoRoute entity) {
		final CargoRouteDTO dto = new CargoRouteDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		dto.setVersion(entity.getVersion());
		dto.setTrain(entity.getTrain());

		return dto;
	}
}
