package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class TicketDTO {
	
	private Integer id;
	
	private Date created;
	
	private Date updated;
	@NotNull
	@Digits(integer=15, fraction=2)
	private Double price;
	@NotNull
	@Range(min=0)
	private Integer passengerId;
	@NotNull
	@Range(min=0)
	private Integer passengerRouteId;
	@NotNull
	@Range(min=0)
	private Integer stationFromId;
	@NotNull
	@Range(min=0)
	private Integer stationToId;

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

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public Integer getPassengerRouteId() {
		return passengerRouteId;
	}

	public void setPassengerRouteId(Integer passengerRouteId) {
		this.passengerRouteId = passengerRouteId;
	}

	public Integer getStationFromId() {
		return stationFromId;
	}

	public void setStationFromId(Integer stationFromId) {
		this.stationFromId = stationFromId;
	}

	public Integer getStationToId() {
		return stationToId;
	}

	public void setStationToId(Integer stationToId) {
		this.stationToId = stationToId;
	}

	@Override
	public String toString() {
		return "TicketDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", price=" + price
				+ ", passengerId=" + passengerId + ", passengerRouteId=" + passengerRouteId + ", stationFromId="
				+ stationFromId + ", stationToId=" + stationToId + "]";
	}

	

}
