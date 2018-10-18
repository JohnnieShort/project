package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

	private ITicketDao dao;

	@Autowired
	public TicketServiceImpl(ITicketDao dao) {
		super();
		this.dao = dao;
	}

	public TicketServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public List<ITicket> find(TicketFilter filter) {
		return dao.find(filter);
	}

}
