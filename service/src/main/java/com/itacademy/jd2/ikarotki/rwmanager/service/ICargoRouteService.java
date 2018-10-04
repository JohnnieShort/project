package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;

public interface ICargoRouteService {
	ICargoRoute get(Integer id);

	List<ICargoRoute> getAll();

	void save(ICargoRoute entity);

	void delete(Integer id);

	void deleteAll();

	ICargoRoute createEntity();
}
