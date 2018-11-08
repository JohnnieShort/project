package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;

@Entity
public class PassengerRoute extends BaseEntity implements IPassengerRoute {
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationFrom;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationTo;
	@Column
	private Date departure;
	@Column
	private Date arrival;
	@Enumerated(EnumType.ORDINAL)
	private PassengerRouteType passengerRouteType;
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Train.class)
	private ITrain train;
	@Column
	private Boolean isActual;
	@Enumerated(EnumType.ORDINAL)
	private Frequency frequency;
	@Column
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

	public PassengerRouteType getPassengerRoutetype() {
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
		return "PassengerRoute [stationFrom=" + stationFrom + ", stationTo=" + stationTo + ", departure=" + departure
				+ ", arrival=" + arrival + ", passengerRoutetype=" + passengerRouteType + ", train=" + train
				+ ", isActual=" + isActual + ", frequency=" + frequency + ", places=" + places + ", getId()=" + getId()
				+ "]";
	}

}
