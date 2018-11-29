package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;
import java.util.Map;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;

public interface IRouteItemDao extends IDao<IRouteItem, Integer>{
	List<IRouteItem> find(RouteItemFilter filter);
	
	long getCount(RouteItemFilter filter);

	List<IRouteItem> getItems(Integer routeId, RouteItemFilter filter);

	Map<Integer, String> getStationsNames(List<IPassengerRoute> enetities, RouteItemFilter routeItemFilter, boolean isFirst);
}
