package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;

public interface IPassengerRoute extends IBaseEntity {
	public IStation getFrom();

	public void setFrom(IStation from);

	public IStation getTo();

	public void setTo(IStation to);

	public Date getDeparture();

	public void setDeparture(Date departure);

	public Date getArrival();

	public void setArrival(Date arrival);

	public PassengerRouteType getPassengerRoutetype();

	public void setPassengerRoutetype(PassengerRouteType passengerRoutetype);

	public ITrain getTrain();

	public void setTrain(ITrain train);

	public Boolean getIsActual();

	public void setIsActual(Boolean isActual);

	public Frequency getFrequency();

	public void setFrequency(Frequency frequency);

	public Integer getPlaces();

	public void setPlaces(Integer places);
}
