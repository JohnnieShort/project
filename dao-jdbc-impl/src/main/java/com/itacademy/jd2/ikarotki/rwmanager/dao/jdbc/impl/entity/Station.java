package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.model.IStation;

public class Station extends BaseEntity implements IStation{
	private String name;
	private Double coordinates;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Double coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", coordinates=" + coordinates + ", getId()=" + getId() + "]";
	}

}
