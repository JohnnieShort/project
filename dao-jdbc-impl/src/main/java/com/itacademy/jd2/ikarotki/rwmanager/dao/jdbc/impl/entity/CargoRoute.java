package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class CargoRoute extends BaseEntity implements ICargoRoute {
	private Integer version;
	private ITrain train;

	public ITrain getTrain() {
		return train;
	}

	public void setTrain(ITrain train) {
		this.train = train;
	}

	@Override
	public Integer getVersion() {
		
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
		
	}

	

}