package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

public class RouteItem extends BaseEntity implements IRouteItem {
	private IPassengerRoute passengerRoute;
	private IStation stationFrom;
	private IStation stationTo;
	private Double time;
	private Integer ordinalNum;

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

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
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
				+ stationTo + ", time=" + time + ", ordinalNum=" + ordinalNum + ", getId()=" + getId() + "]";
	}

}
