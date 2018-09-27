package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;

public interface IWagon extends IBaseEntity {
	public WagonType getWagonType();

	public void setWagonType(WagonType wagonType);;

	public ITrain getTrain();

	public void setTrain(ITrain train);

	public Double getFreightPrice();

	public void setFreightPrice(Double freightPrice);
}
