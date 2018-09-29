package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;

public interface ICargoOrderService {
	ICargoOrder get(Integer id);

	List<ICargoOrder> getAll();

	void save(ICargoOrder entity);

	void delete(Integer id);

	void deleteAll();

	ICargoOrder createEntity();
}
