package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface ILocomotive extends IBaseEntity {
	public Double getPower();

	public void setPower(Double power);

	public String getName();

	public void setName(String name);

}
