package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.LocomotiveFilter;

public interface ILocomotiveDao extends IDao<ILocomotive, Integer>{
	List<ILocomotive> find(LocomotiveFilter filter);
	long getCount(LocomotiveFilter filter);
}
