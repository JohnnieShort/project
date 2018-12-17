package com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter;

public class PassengerRouteFilter extends AbstractFilter {
    private boolean fetchTrain;
    private boolean actual;

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public boolean isFetchTrain() {
        return fetchTrain;
    }

    public void setFetchTrain(boolean fetchTrain) {
        this.fetchTrain = fetchTrain;
    }

}
