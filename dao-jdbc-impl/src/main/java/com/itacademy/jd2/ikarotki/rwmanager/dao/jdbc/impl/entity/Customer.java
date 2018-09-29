package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

public class Customer extends BaseEntity implements ICustomer {
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
