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
	private Date departureTime;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date departureDate;
	

	@NotNull
	@DateTimeFormat(pattern = "hh:mm a")
	private Date arrivalTime;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date arrivalDate;
	

	private Integer ordinalNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
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

	public Date getDepartureTime() {
		return departureTime;
	}

	public Integer getPassengerRouteId() {
		return passengerRouteId;
	}

	public void setPassengerRouteId(Integer passengerRouteId) {
		this.passengerRouteId = passengerRouteId;
	}

	public void setDepartureTime(Date departure) {
		this.departureTime = departure;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrival) {
		this.arrivalTime = arrival;
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
				+ ", stationFromId=" + stationFromId + ", stationToId=" + stationToId + ", departureTime="
				+ departureTime + ", departureDate=" + departureDate + ", arrivalTime=" + arrivalTime + ", arrivalDate="
				+ arrivalDate + ", ordinalNum=" + ordinalNum + "]";
	}

	

}
