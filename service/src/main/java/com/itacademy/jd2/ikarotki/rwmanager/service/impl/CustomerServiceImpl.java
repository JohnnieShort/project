package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICustomerDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.StationFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	private ICustomerDao dao;

	@Autowired
	public CustomerServiceImpl(ICustomerDao dao) {
		super();
		this.dao = dao;
	}

	public CustomerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ICustomer createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICustomer entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new customer created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("customer updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ICustomer get(final Integer id) {
		final ICustomer entity = dao.get(id);
		LOGGER.info("requested customer: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("customer ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All customers were deleted");
	}

	@Override
	public List<ICustomer> getAll() {
		final List<ICustomer> all = dao.selectAll();
		LOGGER.debug("requested list of customerts {}", all);
		return all;
	}

	@Override
	public List<ICustomer> find(CustomerFilter filter) {
		return dao.find(filter);
	}
	
	@Override
	public long getCount(CustomerFilter filter) {
		return dao.getCount(filter);
	}

}
