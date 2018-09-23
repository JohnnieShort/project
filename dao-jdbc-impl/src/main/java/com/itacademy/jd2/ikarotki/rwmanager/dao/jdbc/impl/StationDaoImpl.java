package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IStationDao;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Station;

public class StationDaoImpl extends AbstractDaoImpl<IStation, Integer> implements IStationDao {

	@Override
	public IStation createEntity() {
		return new Station();
	}

	@Override
	public void update(IStation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(IStation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "station";
	}

}
