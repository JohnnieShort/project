package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoRouteFilter;

public interface ICargoRouteService {
	ICargoRoute get(Integer id);

	List<ICargoRoute> getAll();
	@Transactional
	void save(ICargoRoute entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	ICargoRoute createEntity();

	List<ICargoRoute> find(CargoRouteFilter filter);

	long getCount(CargoRouteFilter filter);

	ICargoRoute getFullInfo(Integer id);
}
