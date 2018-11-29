package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

@Entity
public class CargoRoute extends BaseEntity implements ICargoRoute {
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Train.class)
	private ITrain train;
	@Column
    @Version
    private Integer version = 0;
	
	@Override
	public Integer getVersion() {
		
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
		
	}
	public ITrain getTrain() {
		return train;
	}

	public void setTrain(ITrain train) {
		this.train = train;
	}

	

}