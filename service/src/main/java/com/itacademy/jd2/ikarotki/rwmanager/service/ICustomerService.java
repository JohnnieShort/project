package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CustomerFilter;

public interface ICustomerService {
	ICustomer get(Integer id);

	List<ICustomer> getAll();
	@Transactional
	void save(ICustomer entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	ICustomer createEntity();

	List<ICustomer> find(CustomerFilter filter);
	long getCount(CustomerFilter filter);

	ICustomer getFullInfo(Integer id);

}
