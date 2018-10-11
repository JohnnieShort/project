package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;

public class StationServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IStation entity = saveNewStation();

		final IStation entityFromDb = stationService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getLongitude());
		assertNotNull(entityFromDb.getLatitude());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IStation entity = saveNewStation();

		String newName = entity.getName() + "updated";
		entity.setName(newName);
		Thread.sleep(2000);
		stationService.save(entity);

		final IStation entityFromDb = stationService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getLongitude());
		assertNotNull(entityFromDb.getLatitude());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertFalse(entity.getName().equals(entityFromDb.getName()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = stationService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewStation();
		}

		final List<IStation> allEntities = stationService.getAll();

		for (final IStation entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getLongitude());
			assertNotNull(entityFromDb.getLatitude());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IStation entity = saveNewStation();

		assertNotNull(stationService.get(entity.getId()));

		stationService.delete(entity.getId());
		assertNull(stationService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewStation();
		assertNotEquals(0, stationService.getAll().size());
		stationService.deleteAll();
		assertEquals(0, stationService.getAll().size());
	}
}