package com.itacademy.jd2.ikarotki.rwmanager.dao.api.model;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.model.base.IBaseEntity;

public interface ITicket extends IBaseEntity{
	public int getPassengerId();

	public void setPassengerId(int passengerId);

	public int getPassengerRouteId();

	public void setPassengerRouteId(int passengerRouteId);

	public int getPrice();

	public void setPrice(int price);
}
