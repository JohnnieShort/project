package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;

public class Wagon extends BaseEntity implements IWagon {
	private Integer version;
	private WagonType wagonType;
	private ITrain train;
	private Double freightPrice;
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
	public Integer getVersion() {
		
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
		
	}
	
	@Override
	public String toString() {
		return "Wagon [wagonType=" + wagonType + ", train=" + train + ", freightPrice=" + freightPrice + ", capacity="
				+ capacity + ", getId()=" + getId() + "]";
	}

}
