package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class StationDTO {
	@NotNull
	@Range(min=0)
	private Integer id;
	@NotNull
	@Size(min = 1, max = 50)
	private String name;
	@NotNull
	@Digits(integer=15, fraction=2)
	private Double longitude;
	@NotNull
	@Digits(integer=15, fraction=2)
	private Double latitude;
	@NotNull
	private Date created;
	@NotNull
	private Date updated;

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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double coordinates) {
		this.longitude = coordinates;
	}

	@Override
	public String toString() {
		return "StationDTO [id=" + id + ", name=" + name + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", created=" + created + ", updated=" + updated + "]";
	}

}
