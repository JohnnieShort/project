package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoOrderFilter;

public interface ICargoOrderService {
	ICargoOrder get(Integer id);

	List<ICargoOrder> getAll();
	@Transactional
	void save(ICargoOrder entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	ICargoOrder createEntity();

	List<ICargoOrder> find(CargoOrderFilter filter);

	long getCount(CargoOrderFilter filter);

	ICargoOrder getFullInfo(Integer id);
}
