package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;

public class PassengerRoute extends BaseEntity implements IPassengerRoute {
	private Integer version;
	private IStation stationFrom;
	private IStation stationTo;
	private Date departure;
	private Date arrival;
	private PassengerRouteType passengerRoutetype;
	private ITrain train;
	private Boolean isActual;
	private Frequency frequency;
	private Integer places;

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
		return passengerRoutetype;
	}

	public void setPassengerRouteType(PassengerRouteType passengerRoutetype) {
		this.passengerRoutetype = passengerRoutetype;
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
	public Integer getVersion() {
		
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
		
	}

	@Override
	public String toString() {
		return "PassengerRoute [stationFrom=" + stationFrom + ", stationTo=" + stationTo + ", departure=" + departure
				+ ", arrival=" + arrival + ", passengerRoutetype=" + passengerRoutetype + ", train=" + train
				+ ", isActual=" + isActual + ", frequency=" + frequency + ", places=" + places + ", getId()=" + getId()
				+ "]";
	}

	
}
