package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class PassengerDTO {

	private Integer id;

	private Date created;

	private Date updated;
	@NotNull

	private Integer userAccountId;
	@NotNull
	private String street;

	@NotNull
	@Range(min=0)
	private Integer building;
	@NotNull
	@Range(min=0)
	private Integer apartments;
	@NotNull
	private String phone;

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "PassengerDTO [id=" + id + "]";
	}

}
