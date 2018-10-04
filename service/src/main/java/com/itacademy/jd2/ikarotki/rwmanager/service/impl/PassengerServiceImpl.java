package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IPassengerDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;

@Service
public class PassengerServiceImpl implements IPassengerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PassengerServiceImpl.class);
	private IPassengerDao dao;

	@Autowired
	public PassengerServiceImpl(IPassengerDao dao) {
		super();
		this.dao = dao;
	}

	public PassengerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPassenger createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPassenger entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new passenger created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("passenger updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IPassenger get(final Integer id) {
		final IPassenger entity = dao.get(id);
		LOGGER.info("requested passenger: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("passenger ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All passengers were deleted");
	}

	@Override
	public List<IPassenger> getAll() {
		final List<IPassenger> all = dao.selectAll();
		LOGGER.debug("requested list of passengers {}", all);
		return all;
	}

}
