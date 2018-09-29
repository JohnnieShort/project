package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;

public class Ticket extends BaseEntity implements ITicket {
	private Integer passengerId;
	private Integer passengerRouteId;
	private Double price;
	private IPassenger passenger;
	private IPassengerRoute passengerRoute;
	private IStation from;
	private IStation to;
	
	public IStation getFrom() {
		return from;
	}

	public void setFrom(IStation from) {
		this.from = from;
	}

	public IStation getTo() {
		return to;
	}

	public void setTo(IStation to) {
		this.to = to;
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

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public int getPassengerRouteId() {
		return passengerRouteId;
	}

	public void setPassengerRouteId(int passengerRouteId) {
		this.passengerRouteId = passengerRouteId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [passenger=" + passenger + ", passengerRoute=" + passengerRoute + ", price=" + price
				+ ", getId()=" + getId() + "]";
	}
}
