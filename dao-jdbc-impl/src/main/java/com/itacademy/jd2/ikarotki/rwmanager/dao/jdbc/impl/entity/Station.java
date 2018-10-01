package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

public class Station extends BaseEntity implements IStation {
	private String name;
	private Double longitude;
	private Double latitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double coordinates) {
		this.longitude = coordinates;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", longitude=" + longitude + ", latitude=" + latitude + ", getId()=" + getId()
				+ "]";
	}

}
