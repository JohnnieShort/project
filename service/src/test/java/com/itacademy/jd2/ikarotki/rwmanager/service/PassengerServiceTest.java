package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;

public class PassengerServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IPassenger entity = saveNewPassenger();

		final IPassenger entityFromDb = passengerService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getUserAccount());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getUserAccount().getId().equals(entity.getUserAccount().getId()));

	}
	
	@Test
	public void testGetFullInfo() {
		final IPassenger entity = saveNewPassenger();

		final IPassenger entityFromDb = passengerService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getUserAccount());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getUserAccount().getId().equals(entity.getUserAccount().getId()));
		
		assertTrue(entityFromDb.getUserAccount().getEMail().equals(entity.getUserAccount().getEMail()));
		assertTrue(entityFromDb.getUserAccount().getFirstName().equals(entity.getUserAccount().getFirstName()));
		assertTrue(entityFromDb.getUserAccount().getLastName().equals(entity.getUserAccount().getLastName()));
		assertFalse(entityFromDb.getUserAccount().getPassword().equals(entity.getUserAccount().getPassword()));
		assertTrue(entityFromDb.getUserAccount().getRole().equals(entity.getUserAccount().getRole()));
		assertFalse(entityFromDb.getUserAccount().getCreated().equals(entity.getUserAccount().getCreated()));
		assertFalse(entityFromDb.getUserAccount().getUpdated().equals(entity.getUserAccount().getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IPassenger entity = saveNewPassenger();

		IUserAccount newUA = saveNewUserAccount();
		entity.setUserAccount(newUA);
		
		Thread.sleep(2000);
		passengerService.save(entity);

		final IPassenger entityFromDb = passengerService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newUA.getId(), entityFromDb.getUserAccount().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertTrue(entity.getUserAccount().getId().equals(entityFromDb.getUserAccount().getId()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = passengerService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPassenger();
		}

		final List<IPassenger> allEntities = passengerService.getAll();

		for (final IPassenger entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getUserAccount());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	
	@Test
	public void testGetCount() {
		final long intialCount = passengerService.getCount(new PassengerFilter());

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPassenger();
		}

		

		assertEquals(randomObjectsCount + intialCount, passengerService.getCount(new PassengerFilter()));
	}
	
	@Test
	public void testFind() {
		PassengerFilter filter = new PassengerFilter();
		filter.setFetchUserAccount(true);
		filter.setSortColumn("created");
		filter.setSortOrder(true);
		
		final int intialCount = passengerService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPassenger();
		}

		final List<IPassenger> allEntities = passengerService.find(filter);

		for (final IPassenger entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getUserAccount());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

			assertNotNull(entityFromDb.getUserAccount().getId());
			assertNotNull(entityFromDb.getUserAccount().getEMail());
			assertNotNull(entityFromDb.getUserAccount().getFirstName());
			assertNotNull(entityFromDb.getUserAccount().getLastName());
			assertNotNull(entityFromDb.getUserAccount().getPassword());
			assertNotNull(entityFromDb.getUserAccount().getRole());
			assertNotNull(entityFromDb.getUserAccount().getCreated());
			assertNotNull(entityFromDb.getUserAccount().getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}


	@Test
	public void testDelete() {
		final IPassenger entity = saveNewPassenger();

		assertNotNull(passengerService.get(entity.getId()));

		passengerService.delete(entity.getId());
		assertNull(passengerService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewPassenger();
		assertNotEquals(0, passengerService.getAll().size());
		passengerService.deleteAll();
		assertEquals(0, passengerService.getAll().size());
	}
}
