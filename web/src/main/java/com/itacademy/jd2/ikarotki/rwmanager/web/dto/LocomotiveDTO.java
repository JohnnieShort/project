package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LocomotiveDTO {
	@NotNull
	private Integer id;
	@NotNull
	private Date created;
	@NotNull
	private Date updated;
	@Size(min = 1, max = 50)
	private String name;
	@NotNull
	@Digits(integer=15, fraction=2)
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
