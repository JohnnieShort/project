package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;

public interface IPassengerService {
	IPassenger get(Integer id);

	List<IPassenger> getAll();

	void save(IPassenger entity);

	void delete(Integer id);

	void deleteAll();

	IPassenger createEntity();

	List<IPassenger> find(PassengerFilter filter);
}
