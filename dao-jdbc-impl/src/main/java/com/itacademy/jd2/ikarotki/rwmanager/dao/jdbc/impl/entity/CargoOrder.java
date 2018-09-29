package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;

public class CargoOrder extends BaseEntity implements ICargoOrder {
	private ICustomer customer;
	private CargoType cargoType;
	private IStation stationFrom;
	private IStation stationTo;
	private Date date;
	private Double weight;

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
		return "CargoOrder [customer=" + customer + ", cargoType=" + cargoType + ", stationFrom=" + stationFrom
				+ ", stationTo=" + stationTo + ", date=" + date + ", weight=" + weight + ", getId()=" + getId() + "]";
	}

}
