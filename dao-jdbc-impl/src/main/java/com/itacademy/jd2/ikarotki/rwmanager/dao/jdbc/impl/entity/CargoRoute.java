package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;

public class CargoRoute extends BaseEntity implements ICargoRoute {
	private Integer cargoOrderId;
	private Integer trainId;
	private Double price;

	public Integer getCargoOrderId() {
		return cargoOrderId;
	}

	public void setCargoOrderId(Integer cargoOrderId) {
		this.cargoOrderId = cargoOrderId;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CargoRoute [cargoOrderId=" + cargoOrderId + ", trainId=" + trainId + ", price=" + price + ", getId()="
				+ getId() + "]";
	}
	

}
