package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;

public interface IPassenger extends IBaseEntity {

	public IUserAccount getUserAccount();

	public void setUserAccount(IUserAccount userAccount);

	public String getStreet();

	public void setStreet(String street);

	public Integer getBuilding();

	public void setBuilding(Integer building);

	public Integer getApartments();

	public void setApartments(Integer apartments);

	public String getPhone();

	public void setPhone(String phone);
}
