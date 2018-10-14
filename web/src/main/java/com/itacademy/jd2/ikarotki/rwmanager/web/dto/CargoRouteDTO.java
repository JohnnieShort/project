package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class CargoRouteDTO {
	private Integer id;
	private Date created;
	private Date updated;
	private ITrain train;
	private Double price;


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

	
	public ITrain getTrain() {
		return train;
	}

	public void setTrain(ITrain train) {
		this.train = train;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CargoRouteDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", train=" + train
				+ ", price=" + price + "]";
	}

}
