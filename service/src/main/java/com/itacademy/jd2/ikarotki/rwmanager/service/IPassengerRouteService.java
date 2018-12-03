package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;

public interface IPassengerRouteService {
	IPassengerRoute get(Integer id);
	
	IPassengerRoute getFullInfo(Integer id);


	List<IPassengerRoute> getAll();
	@Transactional
	void save(IPassengerRoute entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	IPassengerRoute createEntity();

	List<IPassengerRoute> find(PassengerRouteFilter filter);
	
	long getCount(PassengerRouteFilter filter);

	List<IPassengerRoute> findActual(PassengerRouteFilter filter);

	
}
