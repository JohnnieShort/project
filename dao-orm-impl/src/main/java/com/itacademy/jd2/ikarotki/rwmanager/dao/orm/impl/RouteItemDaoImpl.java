package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IRouteItemDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.PassengerRoute_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.RouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.RouteItem_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Station_;

@Repository
public class RouteItemDaoImpl extends AbstractDaoImpl<IRouteItem, Integer> implements IRouteItemDao {

	protected RouteItemDaoImpl() {
		super(RouteItem.class);

	}

	@Override
	public IRouteItem createEntity() {
		IRouteItem routeItem = new RouteItem();
		return routeItem;
	}

	public IRouteItem getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class); // define returning result
		final Root<RouteItem> from = cq.from(RouteItem.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(RouteItem_.passengerRoute, JoinType.LEFT);
		from.fetch(RouteItem_.stationFrom, JoinType.LEFT);
		from.fetch(RouteItem_.stationTo, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(RouteItem_.id), id)); // where id=?

		final TypedQuery<IRouteItem> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IRouteItem> find(RouteItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class); // define type of result
		final Root<RouteItem> from = cq.from(RouteItem.class);// select from route_item
		cq.select(from); // select what? select *

		if (filter.isFetchPassengerRoute()) {

			from.fetch(RouteItem_.passengerRoute, JoinType.LEFT);
		}

		if (filter.isFetchStationFrom()) {

			from.fetch(RouteItem_.stationFrom, JoinType.LEFT);
		}

		if (filter.isFetchStationTo()) {

			from.fetch(RouteItem_.stationTo, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<IRouteItem> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<RouteItem> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(RouteItem_.created);
		case "updated":
			return from.get(RouteItem_.updated);
		case "id":
			return from.get(RouteItem_.id);
		case "passenger_route_id":
			return from.get(RouteItem_.passengerRoute).get(PassengerRoute_.id);

		case "station_from":
			return from.get(RouteItem_.stationFrom).get(Station_.id);
		case "station_to":
			return from.get(RouteItem_.stationTo).get(Station_.id);
		case "arrival":
			return from.get(RouteItem_.arrival);
		case "departure":
			return from.get(RouteItem_.departure);
		case "ordinalNum":
			return from.get(RouteItem_.ordinalNum);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(RouteItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<RouteItem> from = cq.from(RouteItem.class); // select from cargo_order
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<IRouteItem> getItems(Integer routeId, RouteItemFilter filter) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class); // define type of result
		final Root<RouteItem> from = cq.from(RouteItem.class);// select from route_item
		cq.select(from); // select what? select *
		cq.where(cb.equal(from.get(RouteItem_.passengerRoute).get(PassengerRoute_.id), routeId)); // where
		cq.distinct(true); // to avoid duplicate rows in result // passengerRoute.id=?
		if (filter.isFetchStationFrom()) {

			from.fetch(RouteItem_.stationFrom, JoinType.LEFT);
		}

		if (filter.isFetchStationTo()) {

			from.fetch(RouteItem_.stationTo, JoinType.LEFT);
		}

		final TypedQuery<IRouteItem> q = em.createQuery(cq);

		return q.getResultList();

	}

	@Override
	public Map<Integer, String> getStationsNames(List<IPassengerRoute> entities, RouteItemFilter routeItemFilter,
			boolean isFirst) {
		Map<Integer, String> names = new HashMap<Integer, String>();
		for (IPassengerRoute entity : entities) {
			List<IRouteItem> items = getItems(entity.getId(), routeItemFilter);
			names.put(entity.getId(), getName(isFirst, items, routeItemFilter));
		}
		return names;
	}

	private String getName(boolean isFirst, List<IRouteItem> items, RouteItemFilter filter) {
		int count;
		if (isFirst) {
			count = 0;
		} else {
			count = items.size() - 1;
		}
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<String> cq = cb.createQuery(String.class); // define type of result
		final Root<RouteItem> from = cq.from(RouteItem.class);// select from route_item
		cq.distinct(true);
		if (isFirst) {
			cq.multiselect(from.get(RouteItem_.stationFrom).get(Station_.name)); // select what? select *
		} else {
			cq.multiselect(from.get(RouteItem_.stationTo).get(Station_.name)); // select what? select *
		}
		cq.where(cb.equal(from.get(RouteItem_.ordinalNum), count));
		if (filter.isFetchStationFrom()) {

			// from.fetch(RouteItem_.stationFrom, JoinType.LEFT);
		}

		if (filter.isFetchStationTo()) {

			// from.fetch(RouteItem_.stationTo, JoinType.LEFT);
		}
		final TypedQuery<String> q = em.createQuery(cq);

		return getSingleName(q);
	}

	protected String getSingleName(final TypedQuery<String> q) {
		final List<String> resultList = q.getResultList();
		final int size = resultList.size();
		if (size != 1) {
			return "Items of route not specified";
		}
		return resultList.get(0);
	}

}
