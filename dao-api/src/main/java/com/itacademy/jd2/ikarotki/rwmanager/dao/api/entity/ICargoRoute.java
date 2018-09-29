package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface ICargoRoute extends IBaseEntity {
	public ICargoOrder getCargoOrder();

	public void setCargoOrder(ICargoOrder cargoOrderId);

	public ITrain getTrain();

	public void setTrain(ITrain trainId);

	public Double getPrice();

	public void setPrice(Double price);
}
