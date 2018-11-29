package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class CargoRouteDTO {

	private Integer id;

	private Date created;

	private Date updated;
	@NotNull
	@Range(min = 0)
	private ITrain train;

	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

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
