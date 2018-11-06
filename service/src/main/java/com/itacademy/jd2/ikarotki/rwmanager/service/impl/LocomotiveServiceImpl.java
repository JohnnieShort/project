package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ILocomotiveDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.LocomotiveFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ILocomotiveService;

@Service
public class LocomotiveServiceImpl implements ILocomotiveService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocomotiveServiceImpl.class);
	private ILocomotiveDao dao;

	@Autowired
	public LocomotiveServiceImpl(ILocomotiveDao dao) {
		super();
		this.dao = dao;
	}

	public LocomotiveServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ILocomotive createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ILocomotive entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new locomotive created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("locomotive updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ILocomotive get(final Integer id) {
		final ILocomotive entity = dao.get(id);
		LOGGER.info("requested locomotive: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("locomotive ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All locomotives were deleted");
	}

	@Override
	public List<ILocomotive> getAll() {
		final List<ILocomotive> all = dao.selectAll();
		LOGGER.debug("requested list of locomotives {}", all);
		return all;
	}

	@Override
	public List<ILocomotive> find(LocomotiveFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(LocomotiveFilter filter) {
		
			return dao.getCount(filter);
		
	}

}
