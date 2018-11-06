package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;

public interface IWagonService {
	IWagon get(Integer id);

	List<IWagon> getAll();
	@Transactional
	void save(IWagon entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	IWagon createEntity();

	List<IWagon> find(WagonFilter filter);
	
	long getCount(WagonFilter filter);
}
