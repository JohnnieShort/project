package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;

public class CargoOrderDTO {
	
	private Integer id;
	@NotNull
	private Date created;
	@NotNull
	private Date updated;
	@Digits(fraction = 0, integer = 0)
	private ICargoRoute cargoRoute;
	@NotNull
	private ICustomer customer;
	@NotNull
	private CargoType cargoType;
	@NotNull
	private IStation stationFrom;
	@NotNull
	private IStation stationTo;
	@NotNull
	private Date date;
	@NotNull
	private Double weight;
	@NotNull
	private Double price;
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
				+ ", stationTo=" + stationTo + ", date=" + date + ", weight=" + weight + ", price=" + price + "]";
	}

	
	

}
