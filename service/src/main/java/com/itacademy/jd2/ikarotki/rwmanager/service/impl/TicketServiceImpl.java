package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.TicketDaoImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;

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
			LOGGER.info("new ticket created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("ticket updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ITicket get(final Integer id) {
		final ITicket entity = dao.get(id);
		LOGGER.info("requested ticket: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("ticket ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All tickets were deleted");
	}

	@Override
	public List<ITicket> getAll() {
		final List<ITicket> all = dao.selectAll();
		LOGGER.debug("requested list of tickets {}", all);
		return all;
	}

}
