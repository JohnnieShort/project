package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IPassengerRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.PassengerRoute;

public class PassengerRouteDaoImpl extends AbstractDaoImpl<IPassengerRoute, Integer> implements IPassengerRouteDao {

	@Override
	public IPassengerRoute createEntity() {

		return new PassengerRoute();
	}

	@Override
	public void update(IPassengerRoute entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(IPassengerRoute entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "passenger_route";
	}

}
