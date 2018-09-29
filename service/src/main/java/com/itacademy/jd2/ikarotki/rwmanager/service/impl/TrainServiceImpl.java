package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITrainDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.TrainDaoImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;

public class TrainServiceImpl implements ITrainService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TrainServiceImpl.class);
	private ITrainDao dao = new TrainDaoImpl();

	@Override
	public ITrain createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITrain entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new train created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("train updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ITrain get(final Integer id) {
		final ITrain entity = dao.get(id);
		LOGGER.info("requested train: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("train ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All trains were deleted");
	}

	@Override
	public List<ITrain> getAll() {
		final List<ITrain> all = dao.selectAll();
		LOGGER.debug("requested list of trains {}", all);
		return all;
	}

}
