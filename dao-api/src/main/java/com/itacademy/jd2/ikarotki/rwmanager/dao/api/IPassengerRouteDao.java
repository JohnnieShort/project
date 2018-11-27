package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;

public interface IPassengerRouteDao extends IDao<IPassengerRoute, Integer> {
	List<IPassengerRoute> find(PassengerRouteFilter filter);
	
	long getCount(PassengerRouteFilter filter);

	
}
