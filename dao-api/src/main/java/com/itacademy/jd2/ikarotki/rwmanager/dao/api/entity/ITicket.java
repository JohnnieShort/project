package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface ITicket extends IBaseEntity {

	public IStation getFrom();

	public void setFrom(IStation from);

	public IStation getTo();

	public void setTo(IStation to);

	public Double getPrice();

	public void setPrice(Double price);

	public IPassenger getPassenger();

	public void setPassenger(IPassenger passenger);

	public IPassengerRoute getPassengerRoute();

	public void setPassengerRoute(IPassengerRoute passengerRoute);
}
