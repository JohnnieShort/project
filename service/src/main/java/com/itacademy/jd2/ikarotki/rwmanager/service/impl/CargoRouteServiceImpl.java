package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoRouteService;

@Service
public class CargoRouteServiceImpl implements ICargoRouteService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoRouteServiceImpl.class);
	private ICargoRouteDao dao;

	@Autowired
	public CargoRouteServiceImpl(ICargoRouteDao dao) {
		super();
		this.dao = dao;
	}

	public CargoRouteServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ICargoRoute createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICargoRoute entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new cargo route created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("cargo route updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ICargoRoute get(final Integer id) {
		final ICargoRoute entity = dao.get(id);
		LOGGER.info("requested cargo route: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("cargo route ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All cargo routes were deleted");
	}

	@Override
	public List<ICargoRoute> getAll() {
		final List<ICargoRoute> all = dao.selectAll();
		LOGGER.debug("requested list of cargo routes {}", all);
		return all;
	}

	@Override
	public List<ICargoRoute> find(CargoRouteFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CargoRouteFilter filter) {
		return dao.getCount(filter);
	}

}
