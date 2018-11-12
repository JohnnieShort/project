package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IStationDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.StationFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Station;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Station_;

@Repository
public class StationDaoImpl extends AbstractDaoImpl<IStation, Integer> implements IStationDao {

	public StationDaoImpl() {
		super(Station.class);

	}

	@Override
	public IStation createEntity() {
		IStation station = new Station();
		return station;
	}

	@Override
	public IStation getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IStation> cq = cb.createQuery(IStation.class); // define returning result
		final Root<Station> from = cq.from(Station.class); // define table for select

		cq.select(from); // define what need to be selected

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Station_.id), id)); // where id=?

		final TypedQuery<IStation> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IStation> find(StationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IStation> cq = cb.createQuery(IStation.class); // define type of result
		final Root<Station> from = cq.from(Station.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Station, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to sort property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<IStation> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Station, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Station_.created;
		case "updated":
			return Station_.updated;
		case "id":
			return Station_.id;
		case "name":
			return Station_.name;
		case "longitude":
			return Station_.longitude;
		case "latitude":
			return Station_.latitude;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(StationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Station> from = cq.from(Station.class); // select from station
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
