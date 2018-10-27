package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

public class TicketDTO {
	// @Size(min = 1, max = 50)
	private Integer id;
	private Date created;
	private Date updated;

	private Double price;
	private IPassenger passenger;
	private IPassengerRoute passengerRoute;
	private IStation stationFrom;
	private IStation stationTo;

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

	public IStation getStationFrom() {
		return stationFrom;
	}

	public void setFrom(IStation stationFrom) {
		this.stationFrom = stationFrom;
	}

	public IStation getStationTo() {
		return stationTo;
	}

	public void setTo(IStation stationTo) {
		this.stationTo = stationTo;
	}

	@Override
	public String toString() {
		return "TicketDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", price=" + price
				+ ", passenger=" + passenger + ", passengerRoute=" + passengerRoute + ", stationFrom=" + stationFrom
				+ ", stationTo=" + stationTo + "]";
	}

}
