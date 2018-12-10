package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

public class Passenger extends BaseEntity implements IPassenger{
	private IUserAccount userAccount;
	private String Street;
	private Integer building;
	private Integer apartments;
	private String phone;
	

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	public Integer getApartments() {
		return apartments;
	}

	public void setApartments(Integer apartments) {
		this.apartments = apartments;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccountId) {
		this.userAccount = userAccountId;
	}

	@Override
	public String toString() {
		return "Passenger [Id " + getId() + "]";
	}

}
