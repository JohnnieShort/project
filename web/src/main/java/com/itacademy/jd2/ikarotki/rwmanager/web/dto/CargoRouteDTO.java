package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class CargoRouteDTO {

	private Integer id;

	private Date created;

	private Date updated;
	@NotNull
	@Range(min = 0)
	private Integer trainId;

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

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	@Override
	public String toString() {
		return "CargoRouteDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", trainId=" + trainId
				+ ", version=" + version + "]";
	}

	

}
