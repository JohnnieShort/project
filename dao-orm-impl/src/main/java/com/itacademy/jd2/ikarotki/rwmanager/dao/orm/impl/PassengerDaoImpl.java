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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IPassengerDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Customer_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Passenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Passenger_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.UserAccount_;

public class PassengerDaoImpl extends AbstractDaoImpl<IPassenger, Integer> implements IPassengerDao {

	protected PassengerDaoImpl(Class<? extends IPassenger> entityClass) {
		super(Passenger.class);

	}

	@Override
	public IPassenger createEntity() {
		IPassenger passenger = new Passenger();
		return passenger;
	}

	@Override
	public List<IPassenger> find(PassengerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPassenger> cq = cb.createQuery(IPassenger.class); // define type of result
		final Root<Passenger> from = cq.from(Passenger.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchUserAccount()) {

			from.fetch(Passenger_.userAccount, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<IPassenger> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Passenger> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(Passenger_.created);
		case "updated":
			return from.get(Passenger_.updated);
		case "id":
			return from.get(Passenger_.id);
		case "userAccount":
			return from.get(Passenger_.userAccount).get(UserAccount_.id);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	public IPassenger getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPassenger> cq = cb.createQuery(IPassenger.class); // define returning result
		final Root<Passenger> from = cq.from(Passenger.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Passenger_.userAccount, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Customer_.id), id)); // where id=?

		final TypedQuery<IPassenger> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public long getCount(PassengerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Passenger> from = cq.from(Passenger.class); // select from passenger
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
