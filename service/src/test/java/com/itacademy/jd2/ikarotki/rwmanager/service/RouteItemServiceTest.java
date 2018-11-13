package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;

public class RouteItemServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IRouteItem entity = saveNewRouteItem();

		final IRouteItem entityFromDb = routeItemService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getStationFrom());
		assertNotNull(entityFromDb.getStationTo());
		assertNotNull(entityFromDb.getPassengerRoute());
		assertNotNull(entityFromDb.getArrival());
		assertNotNull(entityFromDb.getDeparture());
		assertNotNull(entityFromDb.getOrdinalNum());
		assertNotNull(entityFromDb.getIsFirst());
		assertNotNull(entityFromDb.getIsLast());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getStationFrom().getId().equals(entity.getStationFrom().getId()));
		assertTrue(entityFromDb.getStationTo().getId().equals(entity.getStationTo().getId()));
		assertTrue(entityFromDb.getPassengerRoute().getId().equals(entity.getPassengerRoute().getId()));
	}

	@Test
	public void testGetFullInfo() {
		final IRouteItem entity = saveNewRouteItem();

		final IRouteItem entityFromDb = routeItemService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		
		
		assertNotNull(entityFromDb.getArrival());
		assertNotNull(entityFromDb.getDeparture());
		assertNotNull(entityFromDb.getOrdinalNum());
		assertNotNull(entityFromDb.getIsFirst());
		assertNotNull(entityFromDb.getIsLast());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getStationFrom().getId().equals(entity.getStationFrom().getId()));
		assertTrue(entityFromDb.getStationTo().getId().equals(entity.getStationTo().getId()));
		assertTrue(entityFromDb.getPassengerRoute().getId().equals(entity.getPassengerRoute().getId()));
		
		assertNotNull(entityFromDb.getStationFrom());
		assertTrue(entityFromDb.getStationFrom().getId().equals(entity.getStationFrom().getId()));
		assertTrue(entityFromDb.getStationFrom().getName().equals(entity.getStationFrom().getName()));
		assertTrue(entityFromDb.getStationFrom().getLatitude().equals(entity.getStationFrom().getLatitude()));
		assertTrue(entityFromDb.getStationFrom().getLongitude().equals(entity.getStationFrom().getLongitude()));
		assertNotNull(entityFromDb.getStationFrom().getCreated());
		assertNotNull(entityFromDb.getStationFrom().getUpdated());
		
		assertNotNull(entityFromDb.getStationTo());
		assertTrue(entityFromDb.getStationTo().getId().equals(entity.getStationTo().getId()));
		assertTrue(entityFromDb.getStationTo().getName().equals(entity.getStationTo().getName()));
		assertTrue(entityFromDb.getStationTo().getLatitude().equals(entity.getStationTo().getLatitude()));
		assertTrue(entityFromDb.getStationTo().getLongitude().equals(entity.getStationTo().getLongitude()));;
		assertNotNull(entityFromDb.getStationTo().getCreated());
		assertNotNull(entityFromDb.getStationTo().getUpdated());
		
		assertNotNull(entityFromDb.getPassengerRoute());
		assertTrue(entityFromDb.getPassengerRoute().getId().equals(entity.getPassengerRoute().getId()));
		assertFalse(entityFromDb.getPassengerRoute().getArrival().equals(entity.getPassengerRoute().getArrival()));
		assertFalse(entityFromDb.getPassengerRoute().getDeparture().equals(entity.getPassengerRoute().getDeparture()));
		assertTrue(entityFromDb.getPassengerRoute().getFrequency().equals(entity.getPassengerRoute().getFrequency()));
		assertTrue(entityFromDb.getPassengerRoute().getIsActual().equals(entity.getPassengerRoute().getIsActual()));
		assertTrue(entityFromDb.getPassengerRoute().getPlaces().equals(entity.getPassengerRoute().getPlaces()));
		assertNotNull(entityFromDb.getPassengerRoute().getTrain());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
	}
	
	@Test
	public void testUpdate() throws InterruptedException {
		final IRouteItem entity = saveNewRouteItem();

		Integer newOrdinalNum = entity.getOrdinalNum() + getRANDOM().nextInt(9);
		entity.setOrdinalNum(newOrdinalNum);
		Thread.sleep(2000);
		routeItemService.save(entity);

		final IRouteItem entityFromDb = routeItemService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newOrdinalNum, entityFromDb.getOrdinalNum());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertNotNull(entityFromDb.getPassengerRoute());
		assertNotNull(entityFromDb.getArrival());
		assertNotNull(entityFromDb.getDeparture());
		assertNotNull(entityFromDb.getOrdinalNum());
		assertNotNull(entityFromDb.getIsFirst());
		assertNotNull(entityFromDb.getIsLast());
	}

	@Test
	public void testGetAll() {
		final int intialCount = routeItemService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRouteItem();
		}

		final List<IRouteItem> allEntities = routeItemService.getAll();

		for (final IRouteItem entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getPassengerRoute());
			assertNotNull(entityFromDb.getArrival());
			assertNotNull(entityFromDb.getDeparture());
			assertNotNull(entityFromDb.getOrdinalNum());
			assertNotNull(entityFromDb.getIsFirst());
			assertNotNull(entityFromDb.getIsLast());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testGetCount() {
		final long intialCount = routeItemService.getCount(new RouteItemFilter());

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRouteItem();
		}

		assertEquals(randomObjectsCount + intialCount, routeItemService.getCount(new RouteItemFilter()));
	}

	@Test
	public void testFind() {
		RouteItemFilter filter = new RouteItemFilter();
		filter.setFetchPassengerRoute(true);
		filter.setFetchStationFrom(true);
		filter.setFetchStationTo(true);
		filter.setSortColumn("created");
		filter.setSortOrder(true);

		final int intialCount = routeItemService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRouteItem();
		}

		final List<IRouteItem> allEntities = routeItemService.find(filter);

		for (final IRouteItem entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getPassengerRoute());
			assertNotNull(entityFromDb.getArrival());
			assertNotNull(entityFromDb.getDeparture());
			assertNotNull(entityFromDb.getOrdinalNum());
			assertNotNull(entityFromDb.getIsFirst());
			assertNotNull(entityFromDb.getIsLast());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

			assertNotNull(entityFromDb.getPassengerRoute().getId());
			assertNotNull(entityFromDb.getPassengerRoute().getArrival());
			assertNotNull(entityFromDb.getPassengerRoute().getDeparture());
			assertNotNull(entityFromDb.getPassengerRoute().getFrequency());
			assertNotNull(entityFromDb.getPassengerRoute().getIsActual());
			assertNotNull(entityFromDb.getPassengerRoute().getStationFrom());
			assertNotNull(entityFromDb.getPassengerRoute().getStationTo());
			assertNotNull(entityFromDb.getPassengerRoute().getTrain());
			assertNotNull(entityFromDb.getPassengerRoute().getPassengerRouteType());
			assertNotNull(entityFromDb.getPassengerRoute().getPlaces());

			assertNotNull(entityFromDb.getStationFrom());
			assertNotNull(entityFromDb.getStationFrom().getId());
			assertNotNull(entityFromDb.getStationFrom().getName());
			assertNotNull(entityFromDb.getStationFrom().getLatitude());
			assertNotNull(entityFromDb.getStationFrom().getLongitude());
			assertNotNull(entityFromDb.getStationFrom().getCreated());
			assertNotNull(entityFromDb.getStationFrom().getUpdated());

			assertNotNull(entityFromDb.getStationTo());
			assertNotNull(entityFromDb.getStationTo().getId());
			assertNotNull(entityFromDb.getStationTo().getName());
			assertNotNull(entityFromDb.getStationTo().getLatitude());
			assertNotNull(entityFromDb.getStationTo().getLongitude());
			assertNotNull(entityFromDb.getStationTo().getCreated());
			assertNotNull(entityFromDb.getStationTo().getUpdated());

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IRouteItem entity = saveNewRouteItem();

		assertNotNull(routeItemService.get(entity.getId()));

		routeItemService.delete(entity.getId());
		assertNull(routeItemService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewRouteItem();
		assertNotEquals(0, routeItemService.getAll().size());
		routeItemService.deleteAll();
		assertEquals(0, routeItemService.getAll().size());
	}
}
