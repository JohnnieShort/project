package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.service.IWagonService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.WagonDTO;

@Component
public class WagonFromDTOConverter implements Function<WagonDTO, IWagon> {
	@Autowired
	private IWagonService wagonService;

	@Override
	public IWagon apply(final WagonDTO dto) {
		final IWagon entity = wagonService.createEntity();
		entity.setId(dto.getId());
		entity.setWagonType(dto.getWagonType());
		entity.setTrain(dto.getTrain());
		entity.setFreightPrice(dto.getFreightPrice());
		entity.setCapacity(dto.getCapacity());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		return entity;
	}

}
