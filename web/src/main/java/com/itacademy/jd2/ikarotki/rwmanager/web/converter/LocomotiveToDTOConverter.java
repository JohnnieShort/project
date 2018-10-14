package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.LocomotiveDTO;

@Component
public class LocomotiveToDTOConverter implements Function<ILocomotive, LocomotiveDTO> {
	@Override
	public LocomotiveDTO apply(final ILocomotive entity) {
		final LocomotiveDTO dto = new LocomotiveDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		dto.setName(entity.getName());
		dto.setPower(entity.getPower());

		return dto;
	}
}
