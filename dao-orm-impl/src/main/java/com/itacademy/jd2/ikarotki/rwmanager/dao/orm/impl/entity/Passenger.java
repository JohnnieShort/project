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

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccountId) {
		this.userAccount = userAccountId;
	}

	@Override
	public String toString() {
		return "Passenger [userAccount=" + userAccount + ", getId()=" + getId() + "]";
	}

}
