package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

public class TicketDTO {
	private Integer id;
	private Date created;
	private Date updated;

	private Double price;
	private IPassenger passenger;
	private IPassengerRoute passengerRoute;
	private IStation from;
	private IStation to;

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
		return "TicketDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", price=" + price
				+ ", passenger=" + passenger + ", passengerRoute=" + passengerRoute + ", from=" + from + ", to=" + to
				+ "]";
	}

}
