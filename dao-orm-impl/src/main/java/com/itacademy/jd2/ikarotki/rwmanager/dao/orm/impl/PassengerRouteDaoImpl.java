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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IPassengerRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.PassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.PassengerRoute_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Train_;

@Repository
public class PassengerRouteDaoImpl extends AbstractDaoImpl<IPassengerRoute, Integer> implements IPassengerRouteDao {

    protected PassengerRouteDaoImpl() {
        super(PassengerRoute.class);

    }

    @Override
    public IPassengerRoute createEntity() {
        IPassengerRoute passengerRoute = new PassengerRoute();
        return passengerRoute;
    }

    @Override
    public IPassengerRoute getFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        final CriteriaQuery<IPassengerRoute> cq = cb.createQuery(IPassengerRoute.class);
        final Root<PassengerRoute> from = cq.from(PassengerRoute.class);

        cq.select(from);

        from.fetch(PassengerRoute_.train, JoinType.LEFT);

        cq.distinct(true);

        cq.where(cb.equal(from.get(PassengerRoute_.id), id));

        final TypedQuery<IPassengerRoute> q = em.createQuery(cq);

        return getSingleResult(q);
    }

    @Override
    public List<IPassengerRoute> find(PassengerRouteFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IPassengerRoute> cq = cb.createQuery(IPassengerRoute.class);
        final Root<PassengerRoute> from = cq.from(PassengerRoute.class);
        cq.select(from);

        if (filter.isFetchTrain()) {

            from.fetch(PassengerRoute_.train, JoinType.LEFT);
        }

        if (filter.isActual()) {
            cq.where(cb.equal(from.get(PassengerRoute_.isActual), true));
        }

        final String sortColumn = filter.getSortColumn();
        if (sortColumn != null) {
            final Path<?> expression = getSortPath(from, sortColumn);
            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));

        }

        final TypedQuery<IPassengerRoute> q = em.createQuery(cq);
        setPaging(filter, q);
        return q.getResultList();
    }

    private Path<?> getSortPath(Root<PassengerRoute> from, String sortColumn) {
        switch (sortColumn) {

        case "created":
            return from.get(PassengerRoute_.created);
        case "updated":
            return from.get(PassengerRoute_.updated);
        case "id":
            return from.get(PassengerRoute_.id);
        case "train_id":
            return from.get(Train_.id);

        case "passenger_route_type":
            return from.get(PassengerRoute_.passengerRouteType);

        case "is_actual":
            return from.get(PassengerRoute_.isActual);
        case "frequency":
            return from.get(PassengerRoute_.frequency);

        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }

    @Override
    public long getCount(PassengerRouteFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<PassengerRoute> from = cq.from(PassengerRoute.class);
        if (filter.isActual()) {
            cq.where(cb.equal(from.get(PassengerRoute_.isActual), true));
        }
        cq.select(cb.count(from));
        final TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
    }

}
