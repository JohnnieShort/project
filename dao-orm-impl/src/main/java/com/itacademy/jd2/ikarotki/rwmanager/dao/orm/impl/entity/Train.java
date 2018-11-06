package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

@Entity
public class Train extends BaseEntity implements ITrain {
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Locomotive.class)
	private ILocomotive locomotive;

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

}