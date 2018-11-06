package com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter;

public class CargoOrderFilter extends AbstractFilter {
	private boolean fetchCargoRoute;
	private boolean fetchCustomer;
	private boolean fetchStationFrom;
	private boolean fetchStationTo;

	public boolean isFetchCargoRoute() {
		return fetchCargoRoute;
	}

	public void setFetchCargoRoute(boolean fetchCargoRoute) {
		this.fetchCargoRoute = fetchCargoRoute;
	}

	public boolean isFetchCustomer() {
		return fetchCustomer;
	}

	public void setFetchCustomer(boolean fetchCustomer) {
		this.fetchCustomer = fetchCustomer;
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
