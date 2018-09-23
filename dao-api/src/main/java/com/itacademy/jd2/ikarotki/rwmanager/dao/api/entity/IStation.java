package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface IStation extends IBaseEntity{
	public String getName();

	public void setName(String name);

	public Double getCoordinates();

	public void setCoordinates(Double coordinates);
}
