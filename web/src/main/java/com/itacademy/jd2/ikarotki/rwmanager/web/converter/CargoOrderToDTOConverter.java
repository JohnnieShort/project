package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CargoOrderDTO;

@Component
public class CargoOrderToDTOConverter implements Function<ICargoOrder, CargoOrderDTO> {
	@Override
	public CargoOrderDTO apply(final ICargoOrder entity) {
		final CargoOrderDTO dto = new CargoOrderDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		if (entity.getCargoRoute() != null) {
			dto.setCargoRouteId(entity.getCargoRoute().getId() != null ? entity.getCargoRoute().getId() : null);
		}
		if (entity.getCustomer() != null) {
			dto.setCustomerId(entity.getCustomer().getId() != null ? entity.getCustomer().getId() : null);
		}
		if (entity.getStationFrom() != null) {
			dto.setStationFromId(entity.getStationFrom().getId() != null ? entity.getStationFrom().getId() : null);
		}
		if (entity.getStationTo() != null) {
			dto.setStationToId(entity.getStationTo().getId() != null ? entity.getStationTo().getId() : null);
		}
		dto.setDate(entity.getDate());
		dto.setWeight(entity.getWeight());
		dto.setPrice(entity.getPrice());

		return dto;
	}
}
