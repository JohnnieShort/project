package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IStationDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.StationDaoImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;

public class StationServiceImpl implements IStationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StationServiceImpl.class);
	private IStationDao dao = new StationDaoImpl();

	@Override
	public IStation createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IStation entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new station created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("station updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IStation get(final Integer id) {
		final IStation entity = dao.get(id);
		LOGGER.info("requested station: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("station ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All stations were deleted");
	}

	@Override
	public List<IStation> getAll() {
		final List<IStation> all = dao.selectAll();
		LOGGER.debug("requested list of stations {}", all);
		return all;
	}

}
