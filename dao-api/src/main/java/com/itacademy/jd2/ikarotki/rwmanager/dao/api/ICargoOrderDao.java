package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoOrderFilter;

public interface ICargoOrderDao extends IDao<ICargoOrder, Integer> {
	//ICargoOrder getFullInfo(Integer id);

	List<ICargoOrder> find(CargoOrderFilter filter);

	long getCount(CargoOrderFilter filter);
}
