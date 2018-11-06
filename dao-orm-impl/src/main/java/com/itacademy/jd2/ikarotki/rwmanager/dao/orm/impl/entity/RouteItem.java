package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

@Entity
public class RouteItem extends BaseEntity implements IRouteItem {
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PassengerRoute.class)
	private IPassengerRoute passengerRoute;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationFrom;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationTo;
	@Column
	private Date departure;
	@Column
	private Date arrival;
	@Column
	private Integer ordinalNum;
	@Column
	private Boolean isFirst;

	private Boolean isLast;

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
		return "RouteItem [passengerRoute=" + passengerRoute + ", stationFrom=" + stationFrom + ", stationTo="
				+ stationTo + ", departure=" + departure + ", arrival=" + arrival + ", ordinalNum=" + ordinalNum
				+ ", isFirst=" + isFirst + ", isLast=" + isLast + ", getId()=" + getId() + "]";
	}

}
