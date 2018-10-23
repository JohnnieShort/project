package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoRouteFilter;

public interface ICargoRouteService {
	ICargoRoute get(Integer id);

	List<ICargoRoute> getAll();

	void save(ICargoRoute entity);

	void delete(Integer id);

	void deleteAll();

	ICargoRoute createEntity();

	List<ICargoRoute> find(CargoRouteFilter filter);

	long getCount(CargoRouteFilter filter);
}
