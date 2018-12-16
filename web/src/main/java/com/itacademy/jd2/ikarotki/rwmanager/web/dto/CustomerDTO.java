package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

public class CustomerDTO {
	
	private Integer id;
	
	private Date created;
	
	private Date updated;
	@NotNull
	@Range(min=0)
	private Integer userAccountId;

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
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
		return "CustomerDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", userAccountId="
				+ userAccountId + "]";
	}

	

}
