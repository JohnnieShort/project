package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface ITrain extends IBaseEntity {
	public ILocomotive getLocomotive();

	public void setLocomotive(ILocomotive locomotive);
}
