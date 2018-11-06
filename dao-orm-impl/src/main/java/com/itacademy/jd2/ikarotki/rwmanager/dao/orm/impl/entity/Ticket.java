package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;

@Entity
public class Ticket extends BaseEntity implements ITicket {
	@Column
	private Double price;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Passenger.class)
	private IPassenger passenger;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PassengerRoute.class)
	private IPassengerRoute passengerRoute;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationFrom;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationTo;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public IPassenger getPassenger() {
		return passenger;
	}

	public void setPassenger(IPassenger passenger) {
		this.passenger = passenger;
	}

	public IPassengerRoute getPassengerRoute() {
		return passengerRoute;
	}

	public void setPassengerRoute(IPassengerRoute passengerRoute) {
		this.passengerRoute = passengerRoute;
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

	@Override
	public String toString() {
		return "Ticket [price=" + price + ", passenger=" + passenger + ", passengerRoute=" + passengerRoute
				+ ", stationFrom=" + stationFrom + ", stationTo=" + stationTo + ", getId()=" + getId() + "]";
	}

}