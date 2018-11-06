package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;

public interface IPassengerDao extends IDao<IPassenger, Integer>{
	List<IPassenger> find(PassengerFilter filter);
	
	long getCount(PassengerFilter filter);
}
