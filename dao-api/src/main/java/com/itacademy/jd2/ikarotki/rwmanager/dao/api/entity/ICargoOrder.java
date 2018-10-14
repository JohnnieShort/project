package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;

public interface ICargoOrder extends IBaseEntity {
	public ICargoRoute getCargoRoute();

	public void setCargoRoute(ICargoRoute cargoRoute);

	public ICustomer getCustomer();

	public void setCustomer(ICustomer customerId);

	public CargoType getCargoType();

	public void setCargoType(CargoType cargoType);

	public IStation getStationFrom();

	public void setStationFrom(IStation stationFrom);

	public IStation getStationTo();

	public void setStationTo(IStation stationTo);

	public Date getDate();

	public void setDate(Date date);

	public Double getWeight();

	public void setWeight(Double weight);
}
