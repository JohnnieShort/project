package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.LocomotiveFilter;

public class LocomotiveServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ILocomotive entity = saveNewLocomotive();

		final ILocomotive entityFromDb = locomotiveService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getName());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}
	
	@Test
	public void testGetFullInfo() {
		final ILocomotive entity = saveNewLocomotive();

		final ILocomotive entityFromDb = locomotiveService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getName());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ILocomotive entity = saveNewLocomotive();

		String newName = entity.getName() + "updated";
		entity.setName(newName);
		Thread.sleep(2000);
		locomotiveService.save(entity);

		final ILocomotive entityFromDb = locomotiveService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertTrue(entity.getName().equals(entityFromDb.getName()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = locomotiveService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewLocomotive();
		}

		final List<ILocomotive> allEntities = locomotiveService.getAll();

		for (final ILocomotive entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getName());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	
	@Test
	public void testFind() {
		LocomotiveFilter filter = new LocomotiveFilter();
		filter.setSortColumn("created");
		filter.setSortOrder(true);
		
		final int intialCount = locomotiveService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewLocomotive();
		}

		final List<ILocomotive> allEntities = locomotiveService.find(filter);

		for (final ILocomotive entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getName());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	
	@Test
	public void testGetCount() {
		
		final int intialCount = locomotiveService.find(new LocomotiveFilter()).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewLocomotive();
		}

		

		assertEquals(randomObjectsCount + intialCount, locomotiveService.find(new LocomotiveFilter()).size());
	}

	@Test
	public void testDelete() {
		final ILocomotive entity = saveNewLocomotive();

		assertNotNull(locomotiveService.get(entity.getId()));

		locomotiveService.delete(entity.getId());
		assertNull(locomotiveService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewLocomotive();
		assertNotEquals(0, locomotiveService.getAll().size());
		locomotiveService.deleteAll();
		assertEquals(0, locomotiveService.getAll().size());
	}
}
