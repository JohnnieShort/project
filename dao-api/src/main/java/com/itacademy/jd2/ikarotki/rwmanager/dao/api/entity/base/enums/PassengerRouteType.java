package com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums;

public enum PassengerRouteType {
	SUBURBAN("suburban"), INTERCITY("intercity"), CROSS_BORDER("crossborder");
	private String name;

    private PassengerRouteType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
	
}
