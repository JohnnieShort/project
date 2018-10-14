package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.service.ILocomotiveService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.LocomotiveDTO;

@Component
public class LocomotiveFromDTOConverter implements Function<LocomotiveDTO, ILocomotive> {
	@Autowired
	private ILocomotiveService locomotiveService;

	@Override
	public ILocomotive apply(final LocomotiveDTO dto) {
		final ILocomotive entity = locomotiveService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		entity.setName(dto.getName());
		entity.setPower(dto.getPower());

		return entity;
	}
}
