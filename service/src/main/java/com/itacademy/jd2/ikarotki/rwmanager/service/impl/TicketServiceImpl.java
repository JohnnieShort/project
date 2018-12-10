package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

	private ITicketDao dao;
	private IRouteItemService routeItemService;
	private IPassengerRouteService routeService;
	final private Double PRICE = 1.5;

	@Autowired
	public TicketServiceImpl(ITicketDao dao, IRouteItemService routeItemService, IPassengerRouteService routeService) {
		super();
		this.dao = dao;
		this.routeItemService = routeItemService;
		this.routeService = routeService;
	}

	public TicketServiceImpl() {
		super();

	}

	@Override
	public ITicket createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITicket entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		entity.setPassengerRoute(routeService.getFullInfo(entity.getPassengerRoute().getId()));
		entity.setPrice(calculatePrice(entity.getPassengerRoute().getPassengerRouteType(),
				entity.getPassengerRoute().getId(), entity.getStationFrom().getId(), entity.getStationTo().getId()));
		if (entity.getId() == null) {
			LOGGER.info("new ticket created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("ticket updated: {}", entity);
			dao.update(entity);
		}
	}

	private Double calculatePrice(PassengerRouteType passengerRouteType, Integer routeId,Integer fromId, Integer toId) {
		routeItemService.getItemsQuantity(routeId, fromId, toId);
		Double price = PRICE * getMultyplier(passengerRouteType);
		return price;
	}

	private Double getMultyplier(PassengerRouteType passengerRouteType) {
		Double multyplier = null;
		switch (passengerRouteType) {
		case SUBURBAN:
			multyplier = 1.0;
			break;
		case INTERCITY:
			multyplier = 1.5;
			break;
		case CROSS_BORDER:
			multyplier = 2.0;
			break;
		default:
			throw new UnsupportedOperationException("no multyplier for:" + passengerRouteType);
		}

		return multyplier;
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

	@Override
	public long getCount(TicketFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ITicket getFullInfo(Integer id) {
		final ITicket entity = dao.getFullInfo(id);
		LOGGER.info("requested ticket: {}", entity);
		return entity;
	}
}
