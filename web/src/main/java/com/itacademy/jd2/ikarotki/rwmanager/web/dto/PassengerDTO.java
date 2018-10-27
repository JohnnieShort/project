package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

public class PassengerDTO {
	// @Size(min = 1, max = 50)
	private Integer id;
	private Date created;
	private Date updated;
	private IUserAccount userAccount;

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccountId) {
		this.userAccount = userAccountId;
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
		return "PassengerDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", userAccount="
				+ userAccount + "]";
	}

}
