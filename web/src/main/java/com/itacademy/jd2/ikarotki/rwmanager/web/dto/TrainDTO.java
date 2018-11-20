package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;

public class TrainDTO {
	
	private Integer id;
	
	private Date created;
	
	private Date updated;
	@NotNull
	
	private ILocomotive locomotive;
	
	private Double track;
	
	

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

	public ILocomotive getLocomotive() {
		return locomotive;
	}

	public void setLocomotive(ILocomotive locomotive) {
		this.locomotive = locomotive;
	}

	@Override
	public String toString() {
		return "TrainDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", locomotive=" + locomotive
				+ ", track=" + track + "]";
	}

	

}
