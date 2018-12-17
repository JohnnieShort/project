package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;

public interface IRouteItemDao extends IDao<IRouteItem, Integer> {
    List<IRouteItem> find(RouteItemFilter filter);

    long getCount(RouteItemFilter filter);

    List<IRouteItem> getItems(Integer routeId, RouteItemFilter filter);

    IRouteItem getLastItem(Integer routeId);

}
