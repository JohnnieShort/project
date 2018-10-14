package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;

public class Ticket extends BaseEntity implements ITicket {

	private Double price;
	private IPassenger passenger;
	private IPassengerRoute passengerRoute;
	private IStation from;
	private IStation to;

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

	@Override
	public String toString() {
		return "Ticket [price=" + price + ", passenger=" + passenger + ", passengerRoute=" + passengerRoute + ", from="
				+ from + ", to=" + to + ", getId()=" + getId() + "]";
	}

}