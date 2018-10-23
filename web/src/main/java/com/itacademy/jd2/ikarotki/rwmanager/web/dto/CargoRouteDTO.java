package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class CargoRouteDTO {
	// @Size(min = 1, max = 50)
	private Integer id;
	private Date created;
	private Date updated;
	private ITrain train;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public ITrain getTrain() {
		return train;
	}

	public void setTrain(ITrain train) {
		this.train = train;
	}

	@Override
	public String toString() {
		return "CargoRouteDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", train=" + train + "]";
	}

}
