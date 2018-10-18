package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;

public class CargoOrderDTO {
	//@Size(min = 1, max = 50)
	private Integer id;
	private Date created;
	private Date updated;
	private ICargoRoute cargoRoute;

	private ICustomer customer;
	private CargoType cargoType;
	private IStation stationFrom;
	private IStation stationTo;
	private Date date;
	private Double weight;
	
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

	public ICargoRoute getCargoRoute() {
		return cargoRoute;
	}

	public void setCargoRoute(ICargoRoute cargoRoute) {
		this.cargoRoute = cargoRoute;
	}

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	public CargoType getCargoType() {
		return cargoType;
	}

	public void setCargoType(CargoType cargoType) {
		this.cargoType = cargoType;
	}

	public IStation getStationFrom() {
		return stationFrom;
	}

	public void setStationFrom(IStation stationFrom) {
		this.stationFrom = stationFrom;
	}

	public IStation getStationTo() {
		return stationTo;
	}

	public void setStationTo(IStation stationTo) {
		this.stationTo = stationTo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "CargoOrderDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", cargoRoute="
				+ cargoRoute + ", customer=" + customer + ", cargoType=" + cargoType + ", stationFrom=" + stationFrom
				+ ", stationTo=" + stationTo + ", date=" + date + ", weight=" + weight + "]";
	}

	

}
