package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;

public class Ticket extends BaseEntity implements ITicket {

	private Double price;
	private IPassenger passenger;
	private IPassengerRoute passengerRoute;
	private IStation stationFrom;
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