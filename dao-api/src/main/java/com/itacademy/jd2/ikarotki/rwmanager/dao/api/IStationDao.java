package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.StationFilter;

public interface IStationDao extends IDao<IStation, Integer> {

	List<IStation> find(StationFilter filter);
	
	long getCount(StationFilter filter);

}
