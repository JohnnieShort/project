package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;

public class CargoOrder extends BaseEntity implements ICargoOrder {
	private Integer customerId;
	private CargoType cargoType;
	private Integer stationIdFrom;
	private Integer stationIdTo;
	private Date date;
	private Double weight;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public CargoType getCargoType() {
		return cargoType;
	}
	public void setCargoType(CargoType cargoType) {
		this.cargoType = cargoType;
	}
	public Integer getStationIdFrom() {
		return stationIdFrom;
	}
	public void setStationIdFrom(Integer stationIdFrom) {
		this.stationIdFrom = stationIdFrom;
	}
	public Integer getStationIdTo() {
		return stationIdTo;
	}
	public void setStationIdTo(Integer stationIdTo) {
		this.stationIdTo = stationIdTo;
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
		return "CargoOrder [customerId=" + customerId + ", cargoType=" + cargoType + ", stationIdFrom=" + stationIdFrom
				+ ", stationIdTo=" + stationIdTo + ", date=" + date + ", weight=" + weight + ", getId()=" + getId()
				+ "]";
	}
	
}
