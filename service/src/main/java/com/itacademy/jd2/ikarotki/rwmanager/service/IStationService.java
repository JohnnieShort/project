package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.StationFilter;

public interface IStationService {
	IStation get(Integer id);

	List<IStation> getAll();
	
	@Transactional
	void save(IStation entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	IStation createEntity();

	List<IStation> find(StationFilter filter);
	
	long getCount(StationFilter filter);
}
