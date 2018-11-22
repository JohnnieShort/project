package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.TrainType;

public interface ITrain extends IBaseEntity {
	
	public ILocomotive getLocomotive();

	public void setLocomotive(ILocomotive locomotive);
	
	public Double getTrack();

	public void setTrack(Double track);
	public TrainType getTrainType();

	public void setTrainType(TrainType traintype);
}
