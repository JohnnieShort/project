package com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter;

public class PassengerRouteFilter extends AbstractFilter{
	private boolean fetchTrain;
	
	public boolean isFetchTrain() {
		return fetchTrain;
	}
	public void setFetchTrain(boolean fetchTrain) {
		this.fetchTrain = fetchTrain;
	}
	
	
	
}
