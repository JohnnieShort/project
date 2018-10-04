package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;

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
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getStationFrom().getId().equals(entity.getStationFrom().getId()));
		assertTrue(entityFromDb.getStationTo().getId().equals(entity.getStationTo().getId()));
		assertTrue(entityFromDb.getPassengerRoute().getId().equals(entity.getPassengerRoute().getId()));
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
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IRouteItem entity = saveNewRouteItem();

		assertNotNull(ticketService.get(entity.getId()));

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
