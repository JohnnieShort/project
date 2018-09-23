package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.CargoRoute;

public class CargoRouteDaoImpl extends AbstractDaoImpl<ICargoRoute, Integer> implements ICargoRouteDao {

	@Override
	public ICargoRoute createEntity() {

		return new CargoRoute();
	}

	@Override
	public void update(ICargoRoute entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(ICargoRoute entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "cargo_route";
	}

}
