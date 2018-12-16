package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoOrderService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoOrderDTO;

@Component
public class CargoOrderFromDTOConverter implements Function<CargoOrderDTO, ICargoOrder> {
	@Autowired
	private ICargoOrderService cargoOrderService;

	@Override
	public ICargoOrder apply(final CargoOrderDTO dto) {
		final ICargoOrder entity = cargoOrderService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		
		entity.getCargoRoute().setId(dto.getCargoRouteId() != null ? dto.getCargoRouteId() : null);
		entity.getCustomer().setId(dto.getCustomerId()!= null ? dto.getCustomerId() : null);
		entity.getStationFrom().setId(dto.getStationFromId()!= null ? dto.getStationFromId() : null);
		entity.getStationTo().setId(dto.getStationToId()!= null ? dto.getStationToId() : null);
		entity.setDate(dto.getDate());
		entity.setWeight(dto.getWeight());
		entity.setPrice(dto.getPrice());

		return entity;
	}
}
