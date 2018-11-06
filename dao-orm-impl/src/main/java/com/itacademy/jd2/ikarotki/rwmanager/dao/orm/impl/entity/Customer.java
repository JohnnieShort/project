package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

@Entity
public class Customer extends BaseEntity implements ICustomer {
	
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
	return "Customer [userAccount=" + userAccount + ", getId()=" + getId() + "]";
}

}
