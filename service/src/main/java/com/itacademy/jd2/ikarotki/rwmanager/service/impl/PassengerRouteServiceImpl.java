package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IPassengerRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.PassengerRouteDaoImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;

public class PassengerRouteServiceImpl implements IPassengerRouteService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PassengerRouteServiceImpl.class);
	private IPassengerRouteDao dao = new PassengerRouteDaoImpl();

	@Override
	public IPassengerRoute createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPassengerRoute entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new passenger route created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("passenger route updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IPassengerRoute get(final Integer id) {
		final IPassengerRoute entity = dao.get(id);
		LOGGER.info("requested passenger route: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("passenger route ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All passenger routes were deleted");
	}

	@Override
	public List<IPassengerRoute> getAll() {
		final List<IPassengerRoute> all = dao.selectAll();
		LOGGER.debug("requested list of passenger routes {}", all);
		return all;
	}

}
