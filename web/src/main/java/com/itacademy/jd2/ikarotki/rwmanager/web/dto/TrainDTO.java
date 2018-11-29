package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class TrainDTO {

	private Integer id;

	private Date created;

	private Date updated;
	
	@NotNull

	private Integer locomotiveId;
	
	private String locomotiveName;

	private Double track;
	
	private String trainType;
	
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public Integer getLocomotiveId() {
		return locomotiveId;
	}

	public void setLocomotiveId(Integer locomotiveId) {
		this.locomotiveId = locomotiveId;
	}


	public String getLocomotiveName() {
		return locomotiveName;
	}

	public void setLocomotiveName(String locomotiveName) {
		this.locomotiveName = locomotiveName;
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

	@Override
	public String toString() {
		return "TrainDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", locomotiveId=" + locomotiveId
				+ ", locomotiveName=" + locomotiveName + ", track=" + track + ", trainType=" + trainType + "]";
	}

}
