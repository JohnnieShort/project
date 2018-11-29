package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;

@Entity
public class PassengerRoute extends BaseEntity implements IPassengerRoute {
	
	
	@Enumerated(EnumType.ORDINAL)
	private PassengerRouteType passengerRouteType;
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Train.class)
	private ITrain train;
	@Column
	private Boolean isActual;
	@Enumerated(EnumType.ORDINAL)
	private Frequency frequency;
	@Column
    @Version
    private Integer version = 0;
	
	@Override
	public Integer getVersion() {
		
		return version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
		
	}
	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	
	public PassengerRouteType getPassengerRouteType() {
		return passengerRouteType;
	}


	public void setPassengerRouteType(PassengerRouteType passengerRouteType) {
		this.passengerRouteType = passengerRouteType;

	}

	public ITrain getTrain() {
		return train;
	}

	public void setTrain(ITrain train) {
		this.train = train;
	}

	public Boolean getIsActual() {
		return isActual;
	}

	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}

	@Override
	public String toString() {
		return "PassengerRoute [passengerRouteType=" + passengerRouteType + ", train=" + train + ", isActual="
				+ isActual + ", frequency=" + frequency + ", getId()=" + getId() + "]";
	}

	

}
