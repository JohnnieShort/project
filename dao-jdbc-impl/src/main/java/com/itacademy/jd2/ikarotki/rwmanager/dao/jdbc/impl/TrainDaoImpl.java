package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITrainDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Train;

public class TrainDaoImpl extends AbstractDaoImpl<ITrain, Integer> implements ITrainDao {

	@Override
	public ITrain createEntity() {
		
		return new Train();
	}

	@Override
	public void update(ITrain entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(ITrain entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTableName() {
		
		return "train";
	}

}
