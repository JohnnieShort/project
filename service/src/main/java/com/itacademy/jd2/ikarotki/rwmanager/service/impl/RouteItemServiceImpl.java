package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IRouteItemDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;

@Service
public class RouteItemServiceImpl implements IRouteItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteItemServiceImpl.class);
    private IRouteItemDao dao;

    @Autowired
    public RouteItemServiceImpl(IRouteItemDao dao) {
        super();
        this.dao = dao;
    }

    public RouteItemServiceImpl() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public IRouteItem createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IRouteItem entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);

        if (entity.getOrdinalNum() == null) {
            Integer id = entity.getPassengerRoute().getId();
            IRouteItem lastItem = dao.getLastItem(id);
            entity.setOrdinalNum(lastItem == null ? 0 : lastItem.getOrdinalNum() + 1);
        }
        if (entity.getId() == null) {
            LOGGER.info("new route item created: {}", entity);
            entity.setCreated(modifedOn);
            dao.insert(entity);
        } else {
            LOGGER.info("route item updated: {}", entity);
            dao.update(entity);
        }
    }

    @Override
    public IRouteItem get(final Integer id) {
        final IRouteItem entity = dao.get(id);
        LOGGER.info("requested route item: {}", entity);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        dao.delete(id);
        LOGGER.info("route item ID: {} was deleted", id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
        LOGGER.warn("All route items were deleted");
    }

    @Override
    public List<IRouteItem> getAll() {
        final List<IRouteItem> all = dao.selectAll();
        LOGGER.debug("requested list of route items {}", all);
        return all;
    }

    @Override
    public List<IRouteItem> find(RouteItemFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(RouteItemFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public IRouteItem getFullInfo(Integer id) {
        final IRouteItem entity = dao.getFullInfo(id);
        LOGGER.info("requested route item: {}", entity);
        return entity;
    }

    @Override
    public List<IRouteItem> getItems(Integer routeId, RouteItemFilter filter) {
        List<IRouteItem> itemsList = dao.getItems(routeId, filter);
        // int i = 0;
        // for(IRouteItem item: itemsList) {
        // item.setOrdinalNum(i);
        // i++;
        // }
        return itemsList;
    }

    @Override
    public Map<Integer, String> getStationsNames(List<IPassengerRoute> entities, RouteItemFilter routeItemFilter,
            boolean isFirst) {
        Map<Integer, String> names = new HashMap<Integer, String>();
        for (IPassengerRoute entity : entities) {
            List<IRouteItem> items = getItems(entity.getId(), routeItemFilter);
            if (items.isEmpty()) {
                names.put(entity.getId(), "items of route not specified.");
                continue;
            }
            if (isFirst) {
                names.put(entity.getId(), items.get(0).getStationFrom().getName());
            } else {
                names.put(entity.getId(), items.get(items.size() - 1).getStationTo().getName());
            }
        }
        return names;
    }

    @Override
    public Map<Integer, Date> getTime(List<IPassengerRoute> entities, RouteItemFilter routeItemFilter,
            boolean isDeparture) {
        Map<Integer, Date> departures = new HashMap<Integer, Date>();
        for (IPassengerRoute entity : entities) {
            List<IRouteItem> items = getItems(entity.getId(), routeItemFilter);
            if (items.isEmpty()) {
                departures.put(entity.getId(), new Date());
                continue;
            }
            if (isDeparture) {
                departures.put(entity.getId(), items.get(0).getDeparture());
            } else {
                departures.put(entity.getId(), items.get(items.size() - 1).getArrival());
            }
        }
        return departures;
    }

    @Override
    public Integer getItemsQuantity(Integer routeId, Integer fromId, Integer toId) {
        RouteItemFilter filter = new RouteItemFilter();
        filter.setFetchStationFrom(true);
        filter.setFetchStationTo(true);
        List<IRouteItem> list = getItems(routeId, filter);
        int from = 0;
        int to = 0;
        for (IRouteItem item : list) {
            if (item.getStationFrom().getId().equals(fromId)) {
                from = item.getOrdinalNum();
            }
            if (item.getStationTo().getId().equals(toId)) {
                to = item.getOrdinalNum();
            }
        }
        int q = (to - from) + 1;
        return q;

    }

    @Override
    public String getRouteName(Integer routeId, RouteItemFilter filter) {
        List<IRouteItem> items = getItems(routeId, filter);
        if (items.size() == 0) {
            return null;
        }
        return items.get(0).getStationFrom().getName() + " -> " + items.get(items.size() - 1).getStationTo().getName();
    }

    @Override
    public Map<Integer, String> getDeparture(List<ITicket> tickets) {
        RouteItemFilter filter = new RouteItemFilter();
        filter.setFetchStationFrom(true);
        filter.setFetchStationTo(true);
        filter.setFetchPassengerRoute(true);
        List<IRouteItem> items = find(filter);
        Map<Integer, String> departure = new HashMap<Integer, String>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd  'at' HH:mm:ss ");
        for (ITicket ticket : tickets) {
            for (IRouteItem item : items) {
                if (ticket.getPassengerRoute().getId() == (item.getPassengerRoute().getId())) {
                    if (item.getStationFrom().getId() == (ticket.getStationFrom().getId())) {
                        departure.put(ticket.getId(), "departure: " + dateFormat.format(item.getDeparture()));
                    }
                }
            }
        }
        return departure;
    }

    @Override
    public Map<Integer, String> getArrival(List<ITicket> tickets) {
        RouteItemFilter filter = new RouteItemFilter();
        filter.setFetchStationFrom(true);
        filter.setFetchStationTo(true);
        filter.setFetchPassengerRoute(true);
        List<IRouteItem> items = find(filter);
        Map<Integer, String> departure = new HashMap<Integer, String>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd  'at' HH:mm:ss ");
        for (ITicket ticket : tickets) {
            for (IRouteItem item : items) {
                if (ticket.getPassengerRoute().getId() == (item.getPassengerRoute().getId())) {
                    if (item.getStationTo().getId() == (ticket.getStationTo().getId())) {
                        departure.put(ticket.getId(), "arrival: " + dateFormat.format(item.getArrival()));
                    }
                }
            }
        }
        return departure;
    }

}
