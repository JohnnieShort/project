package com.itacademy.jd2.ikarotki.rwmanager.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICustomerService;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CustomerDTO;

@Component
public class CustomerFromDTOConverter implements Function<CustomerDTO, ICustomer> {
	@Autowired
	private ICustomerService customerService;

	@Override
	public ICustomer apply(final CustomerDTO dto) {
		final ICustomer entity = customerService.createEntity();
		entity.setId(dto.getId());
		entity.setCreated(dto.getCreated());
		entity.setUpdated(dto.getUpdated());

		entity.setUserAccount(dto.getUserAccount());

		return entity;
	}
}
