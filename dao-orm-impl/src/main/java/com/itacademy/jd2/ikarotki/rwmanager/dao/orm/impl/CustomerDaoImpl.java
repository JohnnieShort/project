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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICustomerDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Customer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Customer_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.UserAccount_;

@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<ICustomer, Integer> implements ICustomerDao {

	protected CustomerDaoImpl() {
		super(Customer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ICustomer createEntity() {
		ICustomer customer = new Customer();
		return customer;
	}

	@Override
	public List<ICustomer> find(CustomerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICustomer> cq = cb.createQuery(ICustomer.class); // define type of result
		final Root<Customer> from = cq.from(Customer.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.isFetchUserAccount()) {

			from.fetch(Customer_.userAccount, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<ICustomer> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public ICustomer getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICustomer> cq = cb.createQuery(ICustomer.class); // define returning result
		final Root<Customer> from = cq.from(Customer.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Customer_.userAccount, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Customer_.id), id)); // where id=?

		final TypedQuery<ICustomer> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	private Path<?> getSortPath(Root<Customer> from, String sortColumn) {
		switch (sortColumn) {

		case "created":
			return from.get(Customer_.created);
		case "updated":
			return from.get(Customer_.updated);
		case "id":
			return from.get(Customer_.id);
		case "user_account_id":
			return from.get(Customer_.userAccount).get(UserAccount_.id);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(CustomerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Customer> from = cq.from(Customer.class); // select from customer
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
