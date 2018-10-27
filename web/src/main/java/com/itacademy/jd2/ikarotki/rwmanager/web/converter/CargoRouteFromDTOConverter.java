package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoRouteDTO;

@Component
public class CargoRouteFromDTOConverter implements Function<CargoRouteDTO, ICargoRoute> {
	@Autowired
	private ICargoRouteService cargoRouteService;

	@Override
	public ICargoRoute apply(final CargoRouteDTO dto) {
		final ICargoRoute entity = cargoRouteService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		entity.setTrain(dto.getTrain());

		return entity;
	}
}
