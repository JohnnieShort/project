package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;

public interface ITrainService {
	ITrain get(Integer id);

	List<ITrain> getAll();

	void save(ITrain entity);

	void delete(Integer id);

	void deleteAll();

	ITrain createEntity();

	List<ITrain> find(TrainFilter filter);
}
