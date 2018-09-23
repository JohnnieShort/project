package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IRouteItemDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.RouteItem;

public class RouteItemDaoImpl extends AbstractDaoImpl<IRouteItem, Integer> implements IRouteItemDao {

	@Override
	public IRouteItem createEntity() {
		return new RouteItem();
	}

	@Override
	public void update(IRouteItem entity) {

	}

	@Override
	public void insert(IRouteItem entity) {

	}

	@Override
	protected String getTableName() {

		return "route_item";
	}
}
