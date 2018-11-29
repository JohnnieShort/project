package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;
import java.util.Map;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;

public interface IWagonDao extends IDao<IWagon, Integer> {
	List<IWagon> find(WagonFilter filter);

	long getCount(WagonFilter filter);

	public Double getPlacesByTrain(ITrain train) ;
}
