package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

@Entity
public class Passenger extends BaseEntity implements IPassenger {

	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
	private IUserAccount userAccount;
	private String street;
	private Integer building;
	private Integer apartments;
	private String phone;
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
