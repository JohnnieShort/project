package com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter;

public class TicketFilter extends AbstractFilter {
	private boolean fetchPassenger;
	private boolean fetchPassengerRoute;
	private boolean fetchStationFrom;
	private boolean fetchStationTo;

	public boolean isFetchPassenger() {
		return fetchPassenger;
	}

	public void setFetchPassenger(boolean fetchPassenger) {
		this.fetchPassenger = fetchPassenger;
	}

	public boolean isFetchPassengerRoute() {
		return fetchPassengerRoute;
	}

	public void setFetchPassengerRoute(boolean fetchPassengerRoute) {
		this.fetchPassengerRoute = fetchPassengerRoute;
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
