package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.IBaseEntity;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;

public interface IPassengerRoute extends IBaseEntity {
	

	public PassengerRouteType getPassengerRouteType();

	public void setPassengerRouteType(PassengerRouteType passengerRoutetype);

	public ITrain getTrain();

	public void setTrain(ITrain train);

	public Boolean getIsActual();

	public void setIsActual(Boolean isActual);

	public Frequency getFrequency();

	public void setFrequency(Frequency frequency);

	public Integer getVersion();

	public void setVersion(Integer version);
}
