package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;

public interface ITrainService {
	ITrain get(Integer id);

	List<ITrain> getAll();
	@Transactional
	void save(ITrain entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	ITrain createEntity();

	List<ITrain> find(TrainFilter filter);
	
	long getCount(TrainFilter filter);

	ITrain getFullInfo(Integer id);

	List<Integer> getIds();
}
