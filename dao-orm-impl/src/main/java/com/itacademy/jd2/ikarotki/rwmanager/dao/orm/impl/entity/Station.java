package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

@Entity
public class Station extends BaseEntity implements IStation {
	@Column
	private String name;
	@Column
	private Double longitude;
	@Column
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
