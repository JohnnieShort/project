package com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter;

public class TrainFilter extends AbstractFilter {
	private boolean fetchLocomotive;

	public boolean isFetchLocomotive() {
		return fetchLocomotive;
	}

	public void setFetchLocomotive(boolean fetchLocomotive) {
		this.fetchLocomotive = fetchLocomotive;
	}

}
