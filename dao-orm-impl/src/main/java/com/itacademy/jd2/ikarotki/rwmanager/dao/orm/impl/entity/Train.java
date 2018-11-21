package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.TrainType;

@Entity
public class Train extends BaseEntity implements ITrain {
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Locomotive.class)
	private ILocomotive locomotive;
	@Column
	private Double track;
	
	@Column
	private TrainType trainType;
	

	

	public Double getTrack() {
		return track;
	}

	public void setTrack(Double track) {
		this.track = track;
	}

	public ILocomotive getLocomotive() {
		return locomotive;
	}

	public void setLocomotive(ILocomotive locomotive) {
		this.locomotive = locomotive;
	}

	@Override
	public String toString() {
		return "Train [locomotive=" + locomotive + ", track=" + track + ", traintype=" + trainType + ", getId()="
				+ getId() + "]";
	}

	@Override
	public TrainType getTraintype() {
		
		return trainType;
	}

	@Override
	public void setTraintype(TrainType trainType) {
		this.trainType = trainType;
		
	}

	

	
	

}
