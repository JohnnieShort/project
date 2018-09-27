package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;

public class Wagon extends BaseEntity implements IWagon{
private WagonType wagonType;
private ITrain train;
private Double freightPrice;
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
	return "Wagon [wagonType=" + wagonType + ", train=" + train + ", freightPrice=" + freightPrice + ", getId()="
			+ getId() + "]";
}

}
