package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;

public class WagonServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IWagon entity = saveNewWagon();

		final IWagon entityFromDb = wagonService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getTrain());
		assertNotNull(entityFromDb.getCapacity());
		assertNotNull(entityFromDb.getFreightPrice());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getTrain().getId().equals(entity.getTrain().getId()));

	}

	@Test
	public void testGetFullInfo() {
		final IWagon entity = saveNewWagon();

		final IWagon entityFromDb = wagonService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getTrain());
		assertNotNull(entityFromDb.getCapacity());
		assertNotNull(entityFromDb.getFreightPrice());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getTrain().getId().equals(entity.getTrain().getId()));
		
		assertNotNull(entityFromDb.getTrain().getCreated());
		assertNotNull(entityFromDb.getTrain().getUpdated());
		assertNotNull(entityFromDb.getTrain().getLocomotive());
		assertNotNull(entityFromDb.getTrain().getId());

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IWagon entity = saveNewWagon();

		Double newFreightPrice = entity.getFreightPrice() + 100.0;
		entity.setFreightPrice(newFreightPrice);
		Thread.sleep(2000);
		wagonService.save(entity);

		final IWagon entityFromDb = wagonService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newFreightPrice, entityFromDb.getFreightPrice());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertTrue(entity.getFreightPrice().equals(entityFromDb.getFreightPrice()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = wagonService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewWagon();
		}

		final List<IWagon> allEntities = wagonService.getAll();

		for (final IWagon entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getTrain());
			assertNotNull(entityFromDb.getCapacity());
			assertNotNull(entityFromDb.getFreightPrice());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testGetCount() {
		final long intialCount = wagonService.getCount(new WagonFilter());

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewWagon();
		}

		assertEquals(randomObjectsCount + intialCount, wagonService.getCount(new WagonFilter()));
	}

	@Test
	public void testFind() {
		WagonFilter filter = new WagonFilter();
		filter.setFetchTrain(true);
		filter.setSortColumn("created");
		filter.setSortOrder(true);

		final int intialCount = wagonService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewWagon();
		}

		final List<IWagon> allEntities = wagonService.find(filter);

		for (final IWagon entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());

			assertNotNull(entityFromDb.getCapacity());
			assertNotNull(entityFromDb.getFreightPrice());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

			assertNotNull(entityFromDb.getTrain());
			assertNotNull(entityFromDb.getTrain().getId());
			assertNotNull(entityFromDb.getTrain().getCreated());
			assertNotNull(entityFromDb.getTrain().getUpdated());
			assertNotNull(entityFromDb.getTrain().getLocomotive());

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IWagon entity = saveNewWagon();

		assertNotNull(wagonService.get(entity.getId()));

		wagonService.delete(entity.getId());
		assertNull(wagonService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewWagon();
		assertNotEquals(0, wagonService.getAll().size());
		wagonService.deleteAll();
		assertEquals(0, wagonService.getAll().size());
	}
}
