package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICustomerDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Customer;

public class CustomerDaoImpl extends AbstractDaoImpl<ICustomer, Integer> implements ICustomerDao {

	@Override
	public ICustomer createEntity() {

		return new Customer();
	}

	@Override
	public void update(ICustomer entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(ICustomer entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "customer";
	}

}
