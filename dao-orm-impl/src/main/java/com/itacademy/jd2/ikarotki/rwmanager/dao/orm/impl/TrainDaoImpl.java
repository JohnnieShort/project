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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITrainDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoOrder_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Locomotive_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Train;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Train_;

public class TrainDaoImpl extends AbstractDaoImpl<ITrain, Integer> implements ITrainDao {

	protected TrainDaoImpl(Class<? extends ITrain> entityClass) {
		super(Train.class);

	}

	@Override
	public ITrain createEntity() {
		ITrain train = new Train();
		return train;
	}

	public ITrain getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ITrain> cq = cb.createQuery(ITrain.class); // define returning result
		final Root<Train> from = cq.from(Train.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Train_.locomotive, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(CargoOrder_.id), id)); // where id=?

		final TypedQuery<ITrain> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ITrain> find(TrainFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITrain> cq = cb.createQuery(ITrain.class); // define type of result
		final Root<Train> from = cq.from(Train.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchLocomotive()) {

			from.fetch(Train_.locomotive, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<ITrain> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<Train> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(Train_.created);
		case "updated":
			return from.get(Train_.updated);
		case "id":
			return from.get(Train_.id);
		case "locomotive":
			return from.get(Train_.locomotive).get(Locomotive_.id);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(TrainFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Train> from = cq.from(Train.class); // select from train
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
