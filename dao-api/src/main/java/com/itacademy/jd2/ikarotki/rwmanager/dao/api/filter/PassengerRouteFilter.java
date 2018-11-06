package com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter;

public class PassengerRouteFilter extends AbstractFilter{
	private boolean fetchTrain;
	private boolean fetchStationFrom;
	private boolean fetchStationTo;
	public boolean isFetchTrain() {
		return fetchTrain;
	}
	public void setFetchTrain(boolean fetchTrain) {
		this.fetchTrain = fetchTrain;
	}
	public boolean isFetchStationFrom() {
		return fetchStationFrom;
	}
	public void setFetchStationFrom(boolean fetchStationFrom) {
		this.fetchStationFrom = fetchStationFrom;
	}
	public boolean isFetchStationTo() {
		return fetchStationTo;
	}
	public void setFetchStationTo(boolean fetchStationTo) {
		this.fetchStationTo = fetchStationTo;
	}
	
	
}
