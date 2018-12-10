package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;

public class UserAccountDTO {
	
	private Integer id;
	
	private Date created;
	
	private Date updated;
	@Email
	private String mail;
	@NotNull
	@Size(min = 1, max = 60)
	private String password;
	
	@Enumerated(EnumType.ORDINAL)
	private Role role;
	@NotNull
	@Size(min = 1, max = 50)
	private String firstName;
	@NotNull
	@Size(min = 1, max = 50)
	private String lastName;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
		return "UserAccountDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", eMail=" + mail
				+ ", password=" + password + ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}
