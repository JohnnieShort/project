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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoOrderDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoOrderFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoOrder_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.CargoRoute_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Customer_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Station_;

@Repository
public class CargoOrderDaoImpl extends AbstractDaoImpl<ICargoOrder, Integer> implements ICargoOrderDao {

	protected CargoOrderDaoImpl() {
		super(CargoOrder.class);

	}

	@Override
	public ICargoOrder createEntity() {
		ICargoOrder cargoOrder = new CargoOrder();
		return cargoOrder;
	}

	@Override
	public ICargoOrder getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICargoOrder> cq = cb.createQuery(ICargoOrder.class); // define returning result
		final Root<CargoOrder> from = cq.from(CargoOrder.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(CargoOrder_.cargoRoute, JoinType.LEFT);
		from.fetch(CargoOrder_.customer, JoinType.LEFT);

		from.fetch(CargoOrder_.stationFrom, JoinType.LEFT);
		from.fetch(CargoOrder_.stationTo, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(CargoOrder_.id), id)); // where id=?

		final TypedQuery<ICargoOrder> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<ICargoOrder> find(CargoOrderFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICargoOrder> cq = cb.createQuery(ICargoOrder.class); // define type of result
		final Root<CargoOrder> from = cq.from(CargoOrder.class);// select from cargo_order
		cq.select(from); // select what? select *

		if (filter.isFetchCargoRoute()) {

			from.fetch(CargoOrder_.cargoRoute, JoinType.LEFT);
		}
		if (filter.isFetchCustomer()) {

			from.fetch(CargoOrder_.customer, JoinType.LEFT);
		}
		if (filter.isFetchStationFrom()) {

			from.fetch(CargoOrder_.stationFrom, JoinType.LEFT);
		}
		if (filter.isFetchStationTo()) {

			from.fetch(CargoOrder_.stationTo, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<ICargoOrder> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(Root<CargoOrder> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(CargoOrder_.created);
		case "updated":
			return from.get(CargoOrder_.updated);
		case "id":
			return from.get(CargoOrder_.id);
		case "cargo_route":
			return from.get(CargoOrder_.cargoRoute).get(CargoRoute_.id);
		case "customer_id":
			return from.get(CargoOrder_.customer).get(Customer_.id);
		case "station_from":
			return from.get(CargoOrder_.stationFrom).get(Station_.id);
		case "station_to":
			return from.get(CargoOrder_.stationTo).get(Station_.id);
		case "date":
			return from.get(CargoOrder_.date);
		case "weight":
			return from.get(CargoOrder_.weight);
		case "price":
			return from.get(CargoOrder_.price);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

//	private SingularAttribute<? super CargoOrder, ?> toMetamodelFormat(String sortColumn) {
//		switch (sortColumn) {
//		case "created":
//			return CargoOrder_.created;
//		case "updated":
//			return CargoOrder_.updated;
//		case "id":
//			return CargoOrder_.id;
//		case "cargoRoute":
//			return CargoOrder_.cargoRoute;
//		case "customer_id":
//			return CargoOrder_.customer;
//		case "cargoType":
//			return CargoOrder_.cargoType;
//		case "stationFrom":
//			return CargoOrder_.stationFrom;
//		case "stationTo":
//			return CargoOrder_.stationTo;
//		case "date":
//			return CargoOrder_.date;
//		case "weight":
//			return CargoOrder_.weight;
//		case "price":
//			return CargoOrder_.price;
//		default:
//			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
//		}
//	}

	@Override
	public long getCount(CargoOrderFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<CargoOrder> from = cq.from(CargoOrder.class); // select from cargo_order
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
