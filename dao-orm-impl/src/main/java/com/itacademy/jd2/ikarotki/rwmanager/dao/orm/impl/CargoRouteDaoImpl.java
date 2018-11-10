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
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoRoute_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Train_;

@Repository
public class CargoRouteDaoImpl extends AbstractDaoImpl<ICargoRoute, Integer> implements ICargoRouteDao {

	protected CargoRouteDaoImpl() {
		super(CargoRoute.class);

	}

	@Override
	public ICargoRoute createEntity() {
		ICargoRoute cargoRoute = new CargoRoute();
		return cargoRoute;
	}

	@Override
	public List<ICargoRoute> find(CargoRouteFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICargoRoute> cq = cb.createQuery(ICargoRoute.class); // define type of result
		final Root<CargoRoute> from = cq.from(CargoRoute.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchTrain()) {

			from.fetch(CargoRoute_.train, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<ICargoRoute> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}
	
	@Override
	public ICargoRoute getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICargoRoute> cq = cb.createQuery(ICargoRoute.class); // define returning result
		final Root<CargoRoute> from = cq.from(CargoRoute.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(CargoRoute_.train, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(CargoRoute_.id), id)); // where id=?

		final TypedQuery<ICargoRoute> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	private Path<?> getSortPath(Root<CargoRoute> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(CargoRoute_.created);
		case "updated":
			return from.get(CargoRoute_.updated);
		case "id":
			return from.get(CargoRoute_.id);
		case "cargoRoute":
			return from.get(CargoRoute_.train).get(Train_.id);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(CargoRouteFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<CargoRoute> from = cq.from(CargoRoute.class); // select from cargo_order
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
