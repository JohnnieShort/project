package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {
	@Column
	private String eMail;
	@Column
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column
	private String firstName;
	@Column
	private String lastName;

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserAccount [eMail=" + eMail + ", password=" + password + ", role=" + role + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", getId()=" + getId() + "]";
	}

}
