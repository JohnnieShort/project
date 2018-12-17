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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IRouteItemDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.PassengerRoute_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.RouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.RouteItem_;
import com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity.Station_;

@Repository
public class RouteItemDaoImpl extends AbstractDaoImpl<IRouteItem, Integer> implements IRouteItemDao {

    protected RouteItemDaoImpl() {
        super(RouteItem.class);

    }

    @Override
    public IRouteItem createEntity() {
        IRouteItem routeItem = new RouteItem();
        return routeItem;
    }

    @Override
    public IRouteItem getFullInfo(final Integer id) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();

        final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class); // define
                                                                               // returning
                                                                               // result
        final Root<RouteItem> from = cq.from(RouteItem.class); // define table
                                                               // for select

        cq.select(from); // define what need to be selected

        from.fetch(RouteItem_.passengerRoute, JoinType.LEFT);
        from.fetch(RouteItem_.stationFrom, JoinType.LEFT);
        from.fetch(RouteItem_.stationTo, JoinType.LEFT);

        cq.distinct(true); // to avoid duplicate rows in result

        // .. where id=...
        cq.where(cb.equal(from.get(RouteItem_.id), id)); // where id=?

        final TypedQuery<IRouteItem> q = em.createQuery(cq);

        return getSingleResult(q);
    }

    @Override
    public List<IRouteItem> find(RouteItemFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class); // define
                                                                               // type
                                                                               // of
                                                                               // result
        final Root<RouteItem> from = cq.from(RouteItem.class);// select from
                                                              // route_item
        cq.select(from); // select what? select *

        if (filter.isFetchPassengerRoute()) {

            from.fetch(RouteItem_.passengerRoute, JoinType.LEFT);
        }

        if (filter.isFetchStationFrom()) {

            from.fetch(RouteItem_.stationFrom, JoinType.LEFT);
        }

        if (filter.isFetchStationTo()) {

            from.fetch(RouteItem_.stationTo, JoinType.LEFT);
        }

        final String sortColumn = filter.getSortColumn();
        if (sortColumn != null) {
            final Path<?> expression = getSortPath(from, sortColumn);
            cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
                                                                          // by
                                                                          // column_name
                                                                          // order
        }

        final TypedQuery<IRouteItem> q = em.createQuery(cq);
        setPaging(filter, q);
        return q.getResultList();
    }

    private Path<?> getSortPath(Root<RouteItem> from, String sortColumn) {
        switch (sortColumn) {

        case "created":
            return from.get(RouteItem_.created);
        case "updated":
            return from.get(RouteItem_.updated);
        case "id":
            return from.get(RouteItem_.id);
        case "passenger_route_id":
            return from.get(RouteItem_.passengerRoute).get(PassengerRoute_.id);

        case "station_from":
            return from.get(RouteItem_.stationFrom).get(Station_.name);
        case "station_to":
            return from.get(RouteItem_.stationTo).get(Station_.name);
        case "arrival":
            return from.get(RouteItem_.arrival);
        case "departure":
            return from.get(RouteItem_.departure);
        case "ordinal_num":
            return from.get(RouteItem_.ordinalNum);

        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }

    @Override
    public long getCount(RouteItemFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
                                                                   // type of
                                                                   // result
        final Root<RouteItem> from = cq.from(RouteItem.class); // select from
                                                               // cargo_order
        cq.select(cb.count(from)); // select what? select count(*)
        final TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult(); // execute query
    }

    @Override
    public List<IRouteItem> getItems(Integer routeId, RouteItemFilter filter) {

        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class); // define
                                                                               // type
                                                                               // of
                                                                               // result
        final Root<RouteItem> from = cq.from(RouteItem.class);// select from
                                                              // route_item
        cq.select(from); // select what? select *
        cq.where(cb.equal(from.get(RouteItem_.passengerRoute).get(PassengerRoute_.id), routeId)); // where

        cq.orderBy(cb.asc(from.get(RouteItem_.ordinalNum)));
        if (filter.isFetchStationFrom()) {

            from.fetch(RouteItem_.stationFrom, JoinType.LEFT);
        }

        if (filter.isFetchStationTo()) {

            from.fetch(RouteItem_.stationTo, JoinType.LEFT);
        }

        final TypedQuery<IRouteItem> q = em.createQuery(cq);

        return q.getResultList();

    }

    @Override
    public IRouteItem getLastItem(Integer routeId) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class);
        final Root<RouteItem> from = cq.from(RouteItem.class);
        cq.select(from);
        cq.where(cb.equal(from.get(RouteItem_.passengerRoute).get(PassengerRoute_.id), routeId));

        cq.orderBy(new OrderImpl(from.get(RouteItem_.ordinalNum), false));

        final TypedQuery<IRouteItem> q = em.createQuery(cq);
        q.setMaxResults(1);
        List<IRouteItem> resultList = q.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
