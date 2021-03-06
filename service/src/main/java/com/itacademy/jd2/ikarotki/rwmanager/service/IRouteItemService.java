package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;

public interface IRouteItemService {
	IRouteItem get(Integer id);

	List<IRouteItem> getAll();
	@Transactional
	void save(IRouteItem entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	IRouteItem createEntity();

	List<IRouteItem> find(RouteItemFilter filter);
	
	long getCount(RouteItemFilter filter);

	IRouteItem getFullInfo(Integer id);

	List<IRouteItem> getItems(Integer routeId, RouteItemFilter filter);

	Map<Integer, String> getStationsNames(List<IPassengerRoute> entities, RouteItemFilter routeItemFilter, boolean isFirst);

	Map<Integer, Date> getTime(List<IPassengerRoute> entities, RouteItemFilter routeItemFilter, boolean isDeparture);

	Integer getItemsQuantity(Integer routeId, Integer fromId, Integer toId);

	String getRouteName(Integer id, RouteItemFilter filter);

	

	Map<Integer, String> getDeparture(List<ITicket> tickets);

	Map<Integer, String> getArrival(List<ITicket> tickets);

	
}
