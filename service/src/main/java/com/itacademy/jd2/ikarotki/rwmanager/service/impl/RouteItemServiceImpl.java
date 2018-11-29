package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IRouteItemDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;

@Service
public class RouteItemServiceImpl implements IRouteItemService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RouteItemServiceImpl.class);
	private IRouteItemDao dao;

	@Autowired
	public RouteItemServiceImpl(IRouteItemDao dao) {
		super();
		this.dao = dao;
	}

	public RouteItemServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IRouteItem createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IRouteItem entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new route item created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("route item updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IRouteItem get(final Integer id) {
		final IRouteItem entity = dao.get(id);
		LOGGER.info("requested route item: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("route item ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All route items were deleted");
	}

	@Override
	public List<IRouteItem> getAll() {
		final List<IRouteItem> all = dao.selectAll();
		LOGGER.debug("requested list of route items {}", all);
		return all;
	}

	@Override
	public List<IRouteItem> find(RouteItemFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(RouteItemFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IRouteItem getFullInfo(Integer id) {
		final IRouteItem entity = dao.getFullInfo(id);
		LOGGER.info("requested route item: {}", entity);
		return entity;
	}

	@Override
	public List<IRouteItem> getItems(Integer routeId, RouteItemFilter filter) {
		List<IRouteItem> itemsList = dao.getItems(routeId, filter);
		return itemsList;
	}

	@Override
	public Map<Integer, String> getStationsNames(List<IPassengerRoute> enetities, RouteItemFilter routeItemFilter, boolean isFirst) {
		Map<Integer, String> firstStationsNames = dao.getStationsNames(enetities, routeItemFilter, isFirst);
		return firstStationsNames;
	}
}
