package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class PassengerRouteDTO {

	private Integer id;

	private Date created;

	private Date updated;

	@NotNull
	
	private String passengerRouteType;
	@NotNull
	
	private Integer trainId;
	@NotNull
	
	private Boolean isActual;
	@NotNull

	private String frequency;
	
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

	public Boolean getIsActual() {
		return isActual;
	}

	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}

	public String getPassengerRouteType() {
		return passengerRouteType;
	}

	public void setPassengerRouteType(String passengerRouteType) {
		this.passengerRouteType = passengerRouteType;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "PassengerRouteDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", passengerRouteType="
				+ passengerRouteType + ", isActual=" + isActual + ", frequency=" + frequency + "]";
	}

}
