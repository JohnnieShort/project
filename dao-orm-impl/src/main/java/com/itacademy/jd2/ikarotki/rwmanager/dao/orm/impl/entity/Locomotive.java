package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;

@Entity
public class Locomotive extends BaseEntity implements ILocomotive {

	@Column
	@Access(value = AccessType.PROPERTY)
	private String name;
	@Column
	private Double power;

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Locomotive [name=" + name + ", power=" + power + ", getId()=" + getId() + "]";
	}

}
