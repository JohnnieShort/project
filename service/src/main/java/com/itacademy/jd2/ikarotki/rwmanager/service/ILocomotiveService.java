package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;

public interface ILocomotiveService {
	ILocomotive get(Integer id);

	List<ILocomotive> getAll();

	void save(ILocomotive entity);

	void delete(Integer id);

	void deleteAll();

	ILocomotive createEntity();
}
