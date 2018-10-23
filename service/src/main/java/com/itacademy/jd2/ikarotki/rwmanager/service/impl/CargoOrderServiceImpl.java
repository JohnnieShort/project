package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoOrderDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoOrderFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICargoOrderService;

@Service
public class CargoOrderServiceImpl implements ICargoOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoOrderServiceImpl.class);
	private ICargoOrderDao dao;

	@Autowired
	public CargoOrderServiceImpl(ICargoOrderDao dao) {
		super();
		this.dao = dao;
	}

	public CargoOrderServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ICargoOrder createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICargoOrder entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new cargo order created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("cargo order updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ICargoOrder get(final Integer id) {
		final ICargoOrder entity = dao.get(id);
		LOGGER.info("requested cargo order: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("cargo order ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All cargo orders were deleted");
	}

	@Override
	public List<ICargoOrder> getAll() {
		final List<ICargoOrder> all = dao.selectAll();
		LOGGER.debug("requested list of cargo orders {}", all);
		return all;
	}

	@Override
	public List<ICargoOrder> find(CargoOrderFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CargoOrderFilter filter) {
		// TODO Auto-generated method stub
		throw new RuntimeException("this method is not implemented");
	}

}
