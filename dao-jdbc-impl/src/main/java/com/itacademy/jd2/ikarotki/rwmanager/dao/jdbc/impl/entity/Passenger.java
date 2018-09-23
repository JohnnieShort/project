package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;

public class Passenger extends BaseEntity implements IPassenger{
private Integer userAccountId;

public Integer getUserAccountId() {
	return userAccountId;
}

public void setUserAccountId(Integer userAccountId) {
	this.userAccountId = userAccountId;
}

@Override
public String toString() {
	return "Passenger [userAccountId=" + userAccountId + ", getId()=" + getId() + "]";
}

}
