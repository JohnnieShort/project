package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.UserAccountDTO;

@Component
public class UserAccountToDTOConverter implements Function<IUserAccount, UserAccountDTO> {
	@Override
	public UserAccountDTO apply(final IUserAccount entity) {
		final UserAccountDTO dto = new UserAccountDTO();

		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		dto.setMail(entity.getEMail());
		dto.setPassword(entity.getPassword());
		dto.setRole(entity.getRole());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());

		return dto;
	}
}
