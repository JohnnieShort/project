package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

public class RouteItemDTO {
	@NotNull
	private Integer id;
	@NotNull
	private Date created;
	@NotNull
	private Date updated;
	@NotNull
	@Range(min=0)
	private IPassengerRoute passengerRoute;
	@NotNull
	@Range(min=0)
	private IStation stationFrom;
	@NotNull
	@Range(min=0)
	private IStation stationTo;
	@NotNull
	private Date departure;
	@NotNull
	private Date arrival;
	@NotNull
	@Range(min=0)
	private Integer ordinalNum;
	@NotNull
	
	private Boolean isFirst;
	@NotNull
	
	private Boolean isLast;

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

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
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

	public Integer getOrdinalNum() {
		return ordinalNum;
	}

	public void setOrdinalNum(Integer ordinalNum) {
		this.ordinalNum = ordinalNum;
	}

	@Override
	public String toString() {
		return "RouteItemDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", passengerRoute="
				+ passengerRoute + ", stationFrom=" + stationFrom + ", stationTo=" + stationTo + ", departure="
				+ departure + ", arrival=" + arrival + ", ordinalNum=" + ordinalNum + ", isFirst=" + isFirst
				+ ", isLast=" + isLast + "]";
	}

}
