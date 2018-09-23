package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;


public interface IPassengerRoute extends IBaseEntity{
	public String getFrom() ;
	public void setFrom(String from);
	public String getTo();
	public void setTo(String to);
	public Double getDeparture();
	public void setDeparture(Double departure);
	public Double getArrival();
	public void setArrival(Double arrival);
	public PassengerRouteType getPassengerRoutetype();
	public void setPassengerRoutetype(PassengerRouteType passengerRoutetype);
	public Integer getTrainId();
	public void setTrainId(Integer trainId);
	public Boolean getIsActual();
	public void setIsActual(Boolean isActual);
}
