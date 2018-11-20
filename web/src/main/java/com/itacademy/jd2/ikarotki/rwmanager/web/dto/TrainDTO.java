package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;

public class TrainDTO {

	private Integer id;

	private Date created;

	private Date updated;
	@NotNull

	private Integer locomotiveId;

	private Double track;

	public Integer getLocomotiveId() {
		return locomotiveId;
	}

	public void setLocomotiveId(Integer locomotiveId) {
		this.locomotiveId = locomotiveId;
	}

	public Double getTrack() {
		return track;
	}

	public void setTrack(Double track) {
		this.track = track;
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

}
