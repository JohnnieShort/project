package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;
@Entity
public class CargoOrder extends BaseEntity implements ICargoOrder {
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CargoRoute.class)
	private ICargoRoute cargoRoute;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	private ICustomer customer;
	@Enumerated(EnumType.ORDINAL)
	private CargoType cargoType;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationFrom;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Station.class)
	private IStation stationTo;
	@Column
	private Date date;
	@Column
	private Double weight;
	@Column
	private Double price;

	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		return "CargoOrder [cargoRoute=" + cargoRoute + ", customer=" + customer + ", cargoType=" + cargoType
				+ ", stationFrom=" + stationFrom + ", stationTo=" + stationTo + ", date=" + date + ", weight=" + weight
				+ ", price=" + price + ", getId()=" + getId() + "]";
	}

}
