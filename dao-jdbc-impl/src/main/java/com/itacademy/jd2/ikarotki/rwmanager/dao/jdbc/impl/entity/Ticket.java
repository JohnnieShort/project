package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.model.ITicket;

public class Ticket extends BaseEntity implements ITicket{
	private Integer passengerId;
	private Integer passengerRouteId;
	private Integer price;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [passengerId=" + passengerId + ", passengerRouteId=" + passengerRouteId + ", price=" + price
				+ ", getId()=" + getId() + "]";
	}
}
