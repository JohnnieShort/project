package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

public interface IStationService {
	IStation get(Integer id);

	List<IStation> getAll();

	void save(IStation entity);

	void delete(Integer id);

	void deleteAll();

	IStation createEntity();
}
