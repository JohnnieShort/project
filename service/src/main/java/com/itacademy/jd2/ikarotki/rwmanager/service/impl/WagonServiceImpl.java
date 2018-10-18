package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IWagonDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IWagonService;

@Service
public class WagonServiceImpl implements IWagonService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WagonServiceImpl.class);
	private IWagonDao dao;

	@Autowired
	public WagonServiceImpl(IWagonDao dao) {
		super();
		this.dao = dao;
	}

	public WagonServiceImpl() {
		super();

	}

	@Override
	public IWagon createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IWagon entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new wagon created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("wagon updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IWagon get(final Integer id) {
		final IWagon entity = dao.get(id);
		LOGGER.info("requested wagon: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("wagon ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All wagons were deleted");
	}

	@Override
	public List<IWagon> getAll() {
		final List<IWagon> all = dao.selectAll();
		LOGGER.debug("requested list of wagons {}", all);
		return all;
	}

	@Override
	public List<IWagon> find(WagonFilter filter) {
		return dao.find(filter);
	}

}
