package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;

public interface IRouteItemService {
	IRouteItem get(Integer id);

    List<IRouteItem> getAll();

    void save(IRouteItem entity);

    void delete(Integer id);

    void deleteAll();

    IRouteItem createEntity();
}
