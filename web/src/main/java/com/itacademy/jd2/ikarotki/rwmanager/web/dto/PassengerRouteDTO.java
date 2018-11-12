package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;

public class PassengerRouteDTO {
	
	private Integer id;
	
	private Date created;
	
	private Date updated;
	@NotNull
	@Range(min=0)
	private IStation stationFrom;
	@NotNull
	@Range(min=0)
	private IStation stationTo;
	@NotNull
	private Date departure;
	@NotNull
	private Date arrival;
	@NotNull
	private PassengerRouteType passengerRouteType;
	@NotNull
	@Range(min=0)
	private ITrain train;
	@NotNull
	@Range(min=0)
	private Boolean isActual;
	@NotNull
	@Range(min=0)
	private Frequency frequency;
	@NotNull
	@Range(min=0)
	private Integer places;

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

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public IStation getStationFrom() {
		return stationFrom;
	}

	public void setStationFrom(IStation stationFrom) {
		this.stationFrom = stationFrom;
	}

	public IStation getStationTo() {
		return stationTo;
	}

	public void setStationTo(IStation stationTo) {
		this.stationTo = stationTo;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public PassengerRouteType getPassengerRouteType() {
		return passengerRouteType;
	}

	public void setPassengerRoutetype(PassengerRouteType passengerRouteType) {
		this.passengerRouteType = passengerRouteType;
	}

	public ITrain getTrain() {
		return train;
	}

	public void setTrain(ITrain train) {
		this.train = train;
	}

	public Boolean getIsActual() {
		return isActual;
	}

	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}

	@Override
	public String toString() {
		return "PassengerRouteDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", stationFrom="
				+ stationFrom + ", stationTo=" + stationTo + ", departure=" + departure + ", arrival=" + arrival
				+ ", passengerRoutetype=" + passengerRouteType + ", train=" + train + ", isActual=" + isActual
				+ ", frequency=" + frequency + ", places=" + places + "]";
	}

}
