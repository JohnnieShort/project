package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.TicketDaoImpl;

public class TicketServiceImpl implements ITicketService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);
	private ITicketDao dao = new TicketDaoImpl();

	@Override
	public ITicket createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITicket entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new brand created:" + entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("brand updated:" + entity);
			dao.update(entity);
		}
	}

	@Override
	public ITicket get(final Integer id) {
		final ITicket entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ITicket> getAll() {
		final List<ITicket> all = dao.selectAll();
		return all;
	}

}
