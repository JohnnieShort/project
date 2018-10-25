package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.WagonDTO;

@Component
public class WagonToDTOConverter implements Function<IWagon, WagonDTO> {
	@Override
	public WagonDTO apply(final IWagon entity) {
		final WagonDTO dto = new WagonDTO();
		dto.setId(entity.getId());
		dto.setWagonType(entity.getWagonType());
		dto.setTrain(entity.getTrain());
		dto.setFreightPrice(entity.getFreightPrice());
		dto.setCapacity(entity.getCapacity());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}
}
