package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.LocomotiveFilter;

public interface ILocomotiveService {
	ILocomotive get(Integer id);

	List<ILocomotive> getAll();
	@Transactional
	void save(ILocomotive entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	ILocomotive createEntity();

	List<ILocomotive> find(LocomotiveFilter filter);

	long getCount(LocomotiveFilter filter);
}
