package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;

public class RouteItem extends BaseEntity implements IRouteItem {
	private Integer passengerRouteId;
	private Integer stationIdFrom;
	private Integer stationIdTo;
	private Double time;
	private Integer ordinalNum;

	public Integer getPassengerRouteId() {
		return passengerRouteId;
	}

	public void setPassengerRouteId(Integer passengerRouteId) {
		this.passengerRouteId = passengerRouteId;
	}

	public Integer getStationIdFrom() {
		return stationIdFrom;
	}

	public void setStationIdFrom(Integer stationIdFrom) {
		this.stationIdFrom = stationIdFrom;
	}

	public Integer getStationIdTo() {
		return stationIdTo;
	}

	public void setStationIdTo(Integer stationIdTo) {
		this.stationIdTo = stationIdTo;
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
		return "RouteItem [passengerRouteId=" + passengerRouteId + ", stationIdFrom=" + stationIdFrom + ", stationIdTo="
				+ stationIdTo + ", time=" + time + ", ordinalNum=" + ordinalNum + ", getId()=" + getId() + "]";
	}

}
