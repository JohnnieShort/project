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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IWagonDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoOrder_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Train_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Wagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Wagon_;

public class WagonDaoImpl extends AbstractDaoImpl<IWagon, Integer> implements IWagonDao {

	protected WagonDaoImpl(Class<? extends IWagon> entityClass) {
		super(Wagon.class);

	}

	@Override
	public IWagon createEntity() {
		IWagon wagon = new Wagon();
		return wagon;
	}

	public IWagon getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IWagon> cq = cb.createQuery(IWagon.class); // define returning result
		final Root<Wagon> from = cq.from(Wagon.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Wagon_.train, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(CargoOrder_.id), id)); // where id=?

		final TypedQuery<IWagon> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IWagon> find(WagonFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IWagon> cq = cb.createQuery(IWagon.class); // define type of result
		final Root<Wagon> from = cq.from(Wagon.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchTrain()) {

			from.fetch(Wagon_.train, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<IWagon> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Wagon> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(Wagon_.created);
		case "updated":
			return from.get(Wagon_.updated);
		case "id":
			return from.get(Wagon_.id);
		case "train":
			return from.get(Wagon_.train).get(Train_.id);
		case "wagonType":
			return from.get(Wagon_.wagonType);
		case "freightPrice":
			return from.get(Wagon_.freightPrice);
		case "capacity":
			return from.get(Wagon_.capacity);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(WagonFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Wagon> from = cq.from(Wagon.class); // select from wagon
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
