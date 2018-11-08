package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class TrainServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ITrain entity = saveNewTrain();

		final ITrain entityFromDb = trainService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getLocomotive());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getLocomotive().getId().equals(entity.getLocomotive().getId()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ITrain entity = saveNewTrain();

		ILocomotive newLocomotive = saveNewLocomotive();
		entity.setLocomotive(newLocomotive);
		Thread.sleep(2000);
		trainService.save(entity);

		final ITrain entityFromDb = trainService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newLocomotive.getId(), entityFromDb.getLocomotive().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		//assertNotEquals(entity.getLocomotive().getId(), entityFromDb.getLocomotive().getId());
	}

	@Test
	public void testGetAll() {
		final int intialCount = trainService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTrain();
		}

		final List<ITrain> allEntities = trainService.getAll();

		for (final ITrain entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getLocomotive());

			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ITrain entity = saveNewTrain();

		assertNotNull(trainService.get(entity.getId()));

		trainService.delete(entity.getId());
		assertNull(trainService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTrain();
		assertNotEquals(0, trainService.getAll().size());
		trainService.deleteAll();
		assertEquals(0, trainService.getAll().size());
	}
}
