package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoRouteFilter;

public interface ICargoRouteDao extends IDao<ICargoRoute, Integer> {
	//ICargoRoute getFullInfo(Integer id);

	List<ICargoRoute> find(CargoRouteFilter filter);

	long getCount(CargoRouteFilter filter);
}
