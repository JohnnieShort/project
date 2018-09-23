package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;

public class PassengerRoute extends BaseEntity implements IPassengerRoute {
	private String from;
	private String to;
	private Double departure;
	private Double arrival;
	private PassengerRouteType passengerRoutetype;
	private Integer trainId;
	private Boolean isActual;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getDeparture() {
		return departure;
	}

	public void setDeparture(Double departure) {
		this.departure = departure;
	}

	public Double getArrival() {
		return arrival;
	}

	public void setArrival(Double arrival) {
		this.arrival = arrival;
	}

	public PassengerRouteType getPassengerRoutetype() {
		return passengerRoutetype;
	}

	public void setPassengerRoutetype(PassengerRouteType passengerRoutetype) {
		this.passengerRoutetype = passengerRoutetype;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public Boolean getIsActual() {
		return isActual;
	}

	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}

	@Override
	public String toString() {
		return "PassengerRoute [from=" + from + ", to=" + to + ", departure=" + departure + ", arrival=" + arrival
				+ ", passengerRoutetype=" + passengerRoutetype + ", trainId=" + trainId + ", isActual=" + isActual
				+ ", getId()=" + getId() + "]";
	}

}
