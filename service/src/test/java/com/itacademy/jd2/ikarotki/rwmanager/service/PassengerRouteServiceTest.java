package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;

public class PassengerRouteServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IPassengerRoute entity = saveNewPassengerRoute();

		final IPassengerRoute entityFromDb = passengerRouteService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getFrom());
		assertNotNull(entityFromDb.getTo());
		assertNotNull(entityFromDb.getTrain());
		assertNotNull(entityFromDb.getDeparture());
		assertNotNull(entityFromDb.getArrival());
		assertNotNull(entityFromDb.getFrequency());
		assertNotNull(entityFromDb.getPlaces());
		assertNotNull(entityFromDb.getPassengerRoutetype());
		assertNotNull(entityFromDb.getIsActual());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getTrain().getId().equals(entity.getTrain().getId()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IPassengerRoute entity = saveNewPassengerRoute();

		Boolean newActual = !(entity.getIsActual());
		entity.setIsActual(newActual);
		Thread.sleep(2000);
		passengerRouteService.save(entity);

		final IPassengerRoute entityFromDb = passengerRouteService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newActual, entityFromDb.getIsActual());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getFrom());
		assertNotNull(entityFromDb.getTo());
		assertNotNull(entityFromDb.getTrain());
		assertNotNull(entityFromDb.getDeparture());
		assertNotNull(entityFromDb.getArrival());
		assertNotNull(entityFromDb.getFrequency());
		assertNotNull(entityFromDb.getPlaces());
		assertNotNull(entityFromDb.getPassengerRoutetype());
		assertNotNull(entityFromDb.getIsActual());

		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertFalse(entity.getIsActual() == entityFromDb.getIsActual());
	}

	@Test
	public void testGetAll() {
		final int intialCount = passengerRouteService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPassengerRoute();
		}

		final List<IPassengerRoute> allEntities = passengerRouteService.getAll();

		for (final IPassengerRoute entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getFrom());
			assertNotNull(entityFromDb.getTo());
			assertNotNull(entityFromDb.getTrain());
			assertNotNull(entityFromDb.getDeparture());
			assertNotNull(entityFromDb.getArrival());
			assertNotNull(entityFromDb.getFrequency());
			assertNotNull(entityFromDb.getPlaces());
			assertNotNull(entityFromDb.getPassengerRoutetype());
			assertNotNull(entityFromDb.getIsActual());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPassengerRoute entity = saveNewPassengerRoute();

		assertNotNull(passengerRouteService.get(entity.getId()));

		passengerRouteService.delete(entity.getId());
		assertNull(passengerRouteService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewPassengerRoute();
		assertNotEquals(0, passengerRouteService.getAll().size());
		passengerRouteService.deleteAll();
		assertEquals(0, passengerRouteService.getAll().size());
	}
}
