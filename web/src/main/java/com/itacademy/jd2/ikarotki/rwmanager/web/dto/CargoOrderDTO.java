package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;

public class CargoOrderDTO {
	
	private Integer id;
	
	private Date created;
	
	private Date updated;
	@NotNull
	@Range(min=0)
	private Integer cargoRouteId;
	@NotNull
	@Range(min=0)
	private Integer customerId;
	@NotNull
	
	private CargoType cargoType;
	@NotNull
	@Range(min=0)
	private Integer stationFromId;
	@NotNull
	@Range(min=0)
	private Integer stationToId;
	@NotNull
	private Date date;
	@NotNull
	@Digits(integer=15, fraction=2)
	private Double weight;
	@NotNull
	@Digits(integer=15, fraction=2)
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

	public CargoType getCargoType() {
		return cargoType;
	}

	public void setCargoType(CargoType cargoType) {
		this.cargoType = cargoType;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getCargoRouteId() {
		return cargoRouteId;
	}

	public void setCargoRouteId(Integer cargoRouteId) {
		this.cargoRouteId = cargoRouteId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getStationFromId() {
		return stationFromId;
	}

	public void setStationFromId(Integer stationFromId) {
		this.stationFromId = stationFromId;
	}

	public Integer getStationToId() {
		return stationToId;
	}

	public void setStationToId(Integer stationToId) {
		this.stationToId = stationToId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CargoOrderDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", cargoRouteId="
				+ cargoRouteId + ", customerId=" + customerId + ", cargoType=" + cargoType + ", stationFromId="
				+ stationFromId + ", stationToId=" + stationToId + ", date=" + date + ", weight=" + weight + ", price="
				+ price + "]";
	}

}
