package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;

public class CargoRouteServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ICargoRoute entity = saveNewCargoRoute();

		final ICargoRoute entityFromDb = cargoRouteService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getTrain());

		
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertTrue(entityFromDb.getTrain().getId().equals(entity.getTrain().getId()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICargoRoute entity = saveNewCargoRoute();

		
		Thread.sleep(2000);
		cargoRouteService.save(entity);

		final ICargoRoute entityFromDb = cargoRouteService.get(entity.getId());

		assertNotNull(entityFromDb);
		
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getTrain());

		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		
	}

	@Test
	public void testGetAll() {
		final int intialCount = cargoRouteService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCargoRoute();
		}

		final List<ICargoRoute> allEntities = cargoRouteService.getAll();

		for (final ICargoRoute entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getTrain());

			
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICargoRoute entity = saveNewCargoRoute();

		assertNotNull(cargoRouteService.get(entity.getId()));

		cargoRouteService.delete(entity.getId());
		assertNull(cargoRouteService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCargoRoute();
		assertNotEquals(0, cargoRouteService.getAll().size());
		cargoRouteService.deleteAll();
		assertEquals(0, cargoRouteService.getAll().size());
	}
}
