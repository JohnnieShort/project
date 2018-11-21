package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.TrainType;

public class Train extends BaseEntity implements ITrain {
	private ILocomotive locomotive;
	private Double track;
	public ILocomotive getLocomotive() {
		return locomotive;
	}

	public void setLocomotive(ILocomotive locomotive) {
		this.locomotive = locomotive;
	}

	@Override
	public String toString() {
		return "Train [locomotive=" + locomotive + ", getId()=" + getId() + "]";
	}

	public Double getTrack() {
		return track;
	}

	public void setTrack(Double track) {
		this.track = track;
	}

	@Override
	public TrainType getTraintype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTraintype(TrainType traintype) {
		// TODO Auto-generated method stub
		
	}

	

}
