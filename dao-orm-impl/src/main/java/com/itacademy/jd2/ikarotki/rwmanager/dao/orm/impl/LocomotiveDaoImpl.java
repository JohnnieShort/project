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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ILocomotiveDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.LocomotiveFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Locomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Locomotive_;

public class LocomotiveDaoImpl extends AbstractDaoImpl<ILocomotive, Integer> implements ILocomotiveDao {

	protected LocomotiveDaoImpl(Class<? extends ILocomotive> entityClass) {
		super(Locomotive.class);

	}

	@Override
	public ILocomotive createEntity() {
		ILocomotive locomotive = new Locomotive();
		return locomotive;
	}

	@Override
	public List<ILocomotive> find(LocomotiveFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ILocomotive> cq = cb.createQuery(ILocomotive.class); // define type of result
		final Root<Locomotive> from = cq.from(Locomotive.class);// select from locomotive
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Locomotive, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to sort property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<ILocomotive> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Locomotive, ?> toMetamodelFormat(String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Locomotive_.created;
		case "updated":
			return Locomotive_.updated;
		case "id":
			return Locomotive_.id;
		case "name":
			return Locomotive_.name;
		case "power":
			return Locomotive_.power;

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(LocomotiveFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Locomotive> from = cq.from(Locomotive.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
