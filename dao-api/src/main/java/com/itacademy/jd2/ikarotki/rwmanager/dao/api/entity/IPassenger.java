package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface IPassenger extends IBaseEntity {

	public IUserAccount getUserAccount();

	public void setUserAccount(IUserAccount userAccount);

}
