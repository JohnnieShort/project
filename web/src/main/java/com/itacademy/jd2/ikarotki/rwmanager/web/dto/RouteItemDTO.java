package com.itacademy.jd2.ikarotki.rwmanager.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class RouteItemDTO {

	private Integer id;

	private Date created;

	private Date updated;
	@NotNull

	private Integer passengerRouteId;
	
	private String stationFromName;
	
	private String stationToName;

	@NotNull

	private Integer stationFromId;
	@NotNull

	private Integer stationToId;
	@NotNull
	@DateTimeFormat(pattern = "hh:mm a")
	private Date departure;
	@NotNull
	@DateTimeFormat(pattern = "hh:mm a")
	private Date arrival;
	

	private Integer ordinalNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationFromName() {
		return stationFromName;
	}

	public void setStationFromName(String stationFromName) {
		this.stationFromName = stationFromName;
	}

	public String getStationToName() {
		return stationToName;
	}

	public void setStationToName(String stationToName) {
		this.stationToName = stationToName;
	}

	public Integer getStationFromId() {
		return stationFromId;
	}

	public void setStationFromId(Integer stationFromId) {
		this.stationFromId = stationFromId;
	}

	public Integer getStationToId() {
		return stationToId;
	}

	public void setStationToId(Integer stationToId) {
		this.stationToId = stationToId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getDeparture() {
		return departure;
	}

	public Integer getPassengerRouteId() {
		return passengerRouteId;
	}

	public void setPassengerRouteId(Integer passengerRouteId) {
		this.passengerRouteId = passengerRouteId;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Integer getOrdinalNum() {
		return ordinalNum;
	}

	public void setOrdinalNum(Integer ordinalNum) {
		this.ordinalNum = ordinalNum;
	}

	@Override
	public String toString() {
		return "RouteItemDTO [id=" + id + ", created=" + created + ", updated=" + updated + ", passengerRouteId="
				+ passengerRouteId + ", stationFromName=" + stationFromName + ", stationToName=" + stationToName
				+ ", stationFromId=" + stationFromId + ", stationToId=" + stationToId + ", departure=" + departure
				+ ", arrival=" + arrival + ", ordinalNum=" + ordinalNum + "]";
	}

}
