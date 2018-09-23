package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;

public interface IUserAccount extends IBaseEntity{

	public String geteMail();

	public void seteMail(String eMail);

	public String getPassword();

	public void setPassword(String password);

	public Role getRole();

	public void setRole(Role role);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

}
