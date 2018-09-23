package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoOrderDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.CargoOrder;

public class CargoOrderDaoImpl extends AbstractDaoImpl<ICargoOrder, Integer> implements ICargoOrderDao {

	@Override
	public ICargoOrder createEntity() {

		return new CargoOrder();
	}

	@Override
	public void update(ICargoOrder entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(ICargoOrder entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "cargo_order";
	}

}
