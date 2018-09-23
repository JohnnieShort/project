package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IPassengerDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Passenger;

public class PassengerDaoImpl extends AbstractDaoImpl<IPassenger, Integer> implements IPassengerDao {

	@Override
	public IPassenger createEntity() {

		return new Passenger();
	}

	@Override
	public void update(IPassenger entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(IPassenger entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "passenger";
	}

}
