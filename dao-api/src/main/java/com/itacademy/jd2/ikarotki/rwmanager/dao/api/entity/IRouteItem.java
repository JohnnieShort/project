package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface IRouteItem extends IBaseEntity {
	public IPassengerRoute getPassengerRoute();

	public void setPassengerRoute(IPassengerRoute passengerRoute);

	public IStation getStationFrom();

	public void setStationFrom(IStation stationFrom);

	public IStation getStationTo();

	public void setStationTo(IStation stationIdTo);

	public Date getDeparture();

	public void setDeparture(Date departure);

	public Date getArrival();

	public void setArrival(Date arrival);

	public Integer getOrdinalNum();

	public void setOrdinalNum(Integer ordinalNum);
}
