package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerDTO;

@Component
public class PassengerFromDTOConverter implements Function<PassengerDTO, IPassenger> {
	@Autowired
	private IPassengerService passengerService;
	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IPassenger apply(final PassengerDTO dto) {
		final IPassenger entity = passengerService.createEntity();
		entity.setId(dto.getId());
		entity.setStreet(dto.getStreet());
		entity.setBuilding(dto.getBuilding());
		entity.setApartments(dto.getApartments());
		entity.setPhone(dto.getPhone());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());
		IUserAccount userAccount= userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId()!=null? dto.getUserAccountId(): null);
		entity.getUserAccount().setId((dto.getUserAccountId()));

		return entity;
	}
}
