package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;

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
	@Enumerated(EnumType.ORDINAL)
	private TrainType trainType;
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
	public TrainType getTrainType() {
		
		return trainType;
	}

	@Override
	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
		
	}

	

	
	

}
