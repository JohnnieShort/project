package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IWagonDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Wagon;

public class WagonDaoImpl extends AbstractDaoImpl<IWagon, Integer> implements IWagonDao {

	@Override
	public IWagon createEntity() {

		return new Wagon();
	}

	@Override
	public void update(IWagon entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(IWagon entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "wagon";
	}

}
