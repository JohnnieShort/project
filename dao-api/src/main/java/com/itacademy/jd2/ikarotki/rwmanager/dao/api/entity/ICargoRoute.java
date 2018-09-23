package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface ICargoRoute extends IBaseEntity {
	public Integer getCargoOrderId();

	public void setCargoOrderId(Integer cargoOrderId);

	public Integer getTrainId();

	public void setTrainId(Integer trainId);

	public Double getPrice();

	public void setPrice(Double price);
}
