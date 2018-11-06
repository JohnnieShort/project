package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoOrder_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.PassengerRoute_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Passenger_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Station_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Ticket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Ticket_;

public class TicketDaoImpl extends AbstractDaoImpl<ITicket, Integer> implements ITicketDao {

	protected TicketDaoImpl(Class<? extends ITicket> entityClass) {
		super(Ticket.class);
	}

	@Override
	public ITicket createEntity() {
		ITicket ticket = new Ticket();
		return ticket;
	}

	public ITicket getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ITicket> cq = cb.createQuery(ITicket.class); // define returning result
		final Root<Ticket> from = cq.from(Ticket.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Ticket_.passengerRoute, JoinType.LEFT);
		from.fetch(Ticket_.passenger, JoinType.LEFT);

		from.fetch(Ticket_.stationFrom, JoinType.LEFT);
		from.fetch(Ticket_.stationTo, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(CargoOrder_.id), id)); // where id=?

		final TypedQuery<ITicket> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ITicket> find(TicketFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITicket> cq = cb.createQuery(ITicket.class); // define type of result
		final Root<Ticket> from = cq.from(Ticket.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchPassengerRoute()) {

			from.fetch(Ticket_.passengerRoute, JoinType.LEFT);
		}
		if (filter.isFetchPassenger()) {

			from.fetch(Ticket_.passenger, JoinType.LEFT);
		}
		if (filter.isFetchStationFrom()) {

			from.fetch(Ticket_.stationFrom, JoinType.LEFT);
		}
		if (filter.isFetchStationTo()) {

			from.fetch(Ticket_.stationTo, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<ITicket> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Ticket> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(Ticket_.created);
		case "updated":
			return from.get(Ticket_.updated);
		case "id":
			return from.get(Ticket_.id);
		case "passengerRoute":
			return from.get(Ticket_.passengerRoute).get(PassengerRoute_.id);
		case "passenger":
			return from.get(Ticket_.passenger).get(Passenger_.id);
		case "stationFrom":
			return from.get(Ticket_.stationFrom).get(Station_.id);
		case "stationTo":
			return from.get(Ticket_.stationTo).get(Station_.id);
		case "price":
			return from.get(Ticket_.price);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(TicketFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Ticket> from = cq.from(Ticket.class); // select from ticket
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
