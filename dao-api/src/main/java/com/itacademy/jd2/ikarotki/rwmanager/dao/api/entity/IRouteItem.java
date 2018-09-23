package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface IRouteItem extends IBaseEntity {
	public Integer getPassengerRouteId();

	public void setPassengerRouteId(Integer passengerRouteId);

	public Integer getStationIdFrom();

	public void setStationIdFrom(Integer stationIdFrom);

	public Integer getStationIdTo();

	public void setStationIdTo(Integer stationIdTo);

	public Double getTime();

	public void setTime(Double time);

	public Integer getOrdinalNum();

	public void setOrdinalNum(Integer ordinalNum);
}
