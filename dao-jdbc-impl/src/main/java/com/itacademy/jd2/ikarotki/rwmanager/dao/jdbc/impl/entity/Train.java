package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

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

	

}
