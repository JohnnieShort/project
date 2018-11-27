package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
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

	List<IRouteItem> getItems(Integer routeId);
}
