package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;

public interface ICargoOrder extends IBaseEntity {
	public Integer getCustomerId();

	public void setCustomerId(Integer customerId);

	public CargoType getCargoType();

	public void setCargoType(CargoType cargoType);

	public Integer getStationIdFrom();

	public void setStationIdFrom(Integer stationIdFrom);

	public Integer getStationIdTo();

	public void setStationIdTo(Integer stationIdTo);

	public Date getDate();

	public void setDate(Date date);

	public Double getWeight();

	public void setWeight(Double weight);
}
