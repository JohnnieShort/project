package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;

public class TrainServiceTest extends AbstractTest {
	
	@Test
	public void testGetTrainsIds() {
		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTrain();
		}
		List<Integer> ids = new ArrayList<Integer>();
		ids = trainService.getIds();
		assertNotNull(ids);
		
	}
	

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
	public void testGetFullInfo() {
		final ITrain entity = saveNewTrain();

		final ITrain entityFromDb = trainService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getLocomotive());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getLocomotive().getId().equals(entity.getLocomotive().getId()));
		assertTrue(entityFromDb.getLocomotive().getPower().equals(entity.getLocomotive().getPower()));
		assertTrue(entityFromDb.getLocomotive().getName().equals(entity.getLocomotive().getName()));
		
		assertNotNull(entityFromDb.getLocomotive().getCreated());
		assertNotNull(entityFromDb.getLocomotive().getUpdated());

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
	public void testGetCount() {
		final long intialCount = trainService.getCount(new TrainFilter());

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTrain();
		}

		assertEquals(randomObjectsCount + intialCount, trainService.getCount(new TrainFilter()));
	}

	@Test
	public void testFind() {
		TrainFilter filter = new TrainFilter();
		filter.setFetchLocomotive(true);
		filter.setSortColumn("created");
		filter.setSortOrder(true);
		
		final int intialCount = trainService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTrain();
		}

		final List<ITrain> allEntities = trainService.find(filter);

		for (final ITrain entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getLocomotive());

			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
			assertNotNull(entityFromDb.getLocomotive().getId());
			assertNotNull(entityFromDb.getLocomotive().getName());
			assertNotNull(entityFromDb.getLocomotive().getPower());
			assertNotNull(entityFromDb.getLocomotive().getCreated());
			assertNotNull(entityFromDb.getLocomotive().getUpdated());

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
