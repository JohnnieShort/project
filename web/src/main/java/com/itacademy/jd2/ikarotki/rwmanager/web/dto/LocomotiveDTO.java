package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

public class LocomotiveDTO {

	private Integer id;
	private Date created;
	private Date updated;
	private String name;
	private Double power;

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

	

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "LocomotiveDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", name=" + name
				+ ", power=" + power + "]";
	}

}
