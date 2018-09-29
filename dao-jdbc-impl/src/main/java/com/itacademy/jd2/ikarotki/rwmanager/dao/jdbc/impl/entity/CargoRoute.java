package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class CargoRoute extends BaseEntity implements ICargoRoute {
	private ICargoOrder cargoOrder;
	private ITrain train;
	private Double price;

	public ICargoOrder getCargoOrder() {
		return cargoOrder;
	}

	public void setCargoOrder(ICargoOrder cargoOrder) {
		this.cargoOrder = cargoOrder;
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
		return "CargoRoute [cargoOrder=" + cargoOrder + ", train=" + train + ", price=" + price + ", getId()="
				+ getId() + "]";
	}
	

}
