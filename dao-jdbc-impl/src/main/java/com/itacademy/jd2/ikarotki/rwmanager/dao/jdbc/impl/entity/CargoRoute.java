package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class CargoRoute extends BaseEntity implements ICargoRoute {
	
	private ITrain train;
	private Double price;

	

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
		return "CargoRoute [train=" + train + ", price=" + price + ", getId()="
				+ getId() + "]";
	}
	

}
