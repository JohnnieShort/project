package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;

@Entity
public class Wagon extends BaseEntity implements IWagon {
	@Enumerated(EnumType.ORDINAL)
	private WagonType wagonType;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Train.class)
	private ITrain train;
	@Column
	private Double freightPrice;
	@Column
	private Double capacity;

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public WagonType getWagonType() {
		return wagonType;
	}

	public void setWagonType(WagonType wagonType) {
		this.wagonType = wagonType;
	}

	public ITrain getTrain() {
		return train;
	}

	public void setTrain(ITrain train) {
		this.train = train;
	}

	public Double getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(Double freightPrice) {
		this.freightPrice = freightPrice;
	}

	@Override
	public String toString() {
		return "Wagon [wagonType=" + wagonType + ", train=" + train + ", freightPrice=" + freightPrice + ", capacity="
				+ capacity + ", getId()=" + getId() + "]";
	}

}
