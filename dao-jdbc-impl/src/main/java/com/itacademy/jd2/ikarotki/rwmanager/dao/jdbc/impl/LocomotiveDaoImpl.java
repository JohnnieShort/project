package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ILocomotiveDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Locomotive;

public class LocomotiveDaoImpl extends AbstractDaoImpl<ILocomotive, Integer> implements ILocomotiveDao {

	@Override
	public ILocomotive createEntity() {

		return new Locomotive();
	}

	@Override
	public void update(ILocomotive entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(ILocomotive entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "locomotive";
	}

}
