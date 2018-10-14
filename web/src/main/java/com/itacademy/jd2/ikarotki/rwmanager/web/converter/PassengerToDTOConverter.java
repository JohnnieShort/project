package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerDTO;

@Component
public class PassengerToDTOConverter implements Function<IPassenger, PassengerDTO> {
	@Override
	public PassengerDTO apply(final IPassenger entity) {
		final PassengerDTO dto = new PassengerDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		dto.setUserAccount(entity.getUserAccount());

		return dto;
	}
}
