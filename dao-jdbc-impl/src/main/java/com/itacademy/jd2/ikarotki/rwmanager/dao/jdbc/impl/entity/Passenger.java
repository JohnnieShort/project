package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

public class Passenger extends BaseEntity implements IPassenger{
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
