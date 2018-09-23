package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;

public class Wagon extends BaseEntity implements IWagon{
private WagonType wagonType;
private Integer trainId;
private Double freightPrice;
public WagonType getWagonType() {
	return wagonType;
}
public void setWagonType(WagonType wagonType) {
	this.wagonType = wagonType;
}
public Integer getTrainId() {
	return trainId;
}
public void setTrainId(Integer trainId) {
	this.trainId = trainId;
}
public Double getFreightPrice() {
	return freightPrice;
}
public void setFreightPrice(Double freightPrice) {
	this.freightPrice = freightPrice;
}
@Override
public String toString() {
	return "Wagon [wagonType=" + wagonType + ", trainId=" + trainId + ", freightPrice=" + freightPrice + ", getId()="
			+ getId() + "]";
}

}
