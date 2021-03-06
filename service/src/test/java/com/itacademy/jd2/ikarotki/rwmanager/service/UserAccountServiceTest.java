package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.utils.Password;

public class UserAccountServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IUserAccount entity = saveNewUserAccount();

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getPassword());
		assertNotNull(entityFromDb.getEMail());
		assertNotNull(entityFromDb.getRole());
		assertNotNull(entityFromDb.getFirstName());
		assertNotNull(entityFromDb.getLastName());
		assertNotEquals(entity.getPassword(),entityFromDb.getPassword());
		assertTrue(Password.checkPassword(entity.getPassword(), entityFromDb.getPassword()));
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		
		assertTrue(entityFromDb.getLastName().equals(entity.getLastName()));
	}

	@Test
	public void testGetFullInfo() {
		final IUserAccount entity = saveNewUserAccount();

		final IUserAccount entityFromDb = userAccountService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getPassword());
		assertNotNull(entityFromDb.getEMail());
		assertNotNull(entityFromDb.getRole());
		assertNotNull(entityFromDb.getFirstName());
		assertNotNull(entityFromDb.getLastName());
		assertNotEquals(entity.getPassword(),entityFromDb.getPassword());
		assertTrue(Password.checkPassword(entity.getPassword(), entityFromDb.getPassword()));
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		
		assertTrue(entityFromDb.getLastName().equals(entity.getLastName()));
		assertTrue(entityFromDb.getFirstName().equals(entity.getFirstName()));
		assertTrue(entityFromDb.getEMail().equals(entity.getEMail()));
		assertTrue(entityFromDb.getRole().equals(entity.getRole()));
		assertTrue(entityFromDb.getId().equals(entity.getId()));
	}
	
	@Test
	public void testGetByEMail() {
		final IUserAccount entity = saveNewUserAccount();

		final IUserAccount entityFromDb = userAccountService.getByEmail(entity.getEMail());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getPassword());
		assertNotNull(entityFromDb.getEMail());
		assertNotNull(entityFromDb.getRole());
		assertNotNull(entityFromDb.getFirstName());
		assertNotNull(entityFromDb.getLastName());
		assertNotEquals(entity.getPassword(),entityFromDb.getPassword());
		assertTrue(Password.checkPassword(entity.getPassword(), entityFromDb.getPassword()));
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		
		assertTrue(entityFromDb.getLastName().equals(entity.getLastName()));
		assertTrue(entityFromDb.getFirstName().equals(entity.getFirstName()));
		assertTrue(entityFromDb.getEMail().equals(entity.getEMail()));
		assertTrue(entityFromDb.getRole().equals(entity.getRole()));
		assertTrue(entityFromDb.getId().equals(entity.getId()));
	}
	
	@Test
	public void testUpdate() throws InterruptedException {
		final IUserAccount entity = saveNewUserAccount();

		String newLastName = entity.getLastName() + " updated";
		entity.setLastName(newLastName);
		Thread.sleep(2000);
		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newLastName, entityFromDb.getLastName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertTrue(entity.getLastName().equals(entityFromDb.getLastName()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = userAccountService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}

		final List<IUserAccount> allEntities = userAccountService.getAll();

		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getEMail());
			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getFirstName());
			assertNotNull(entityFromDb.getLastName());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testGetCount() {
		final long intialCount = userAccountService.getCount(new UserAccountFilter());

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}

		assertEquals(randomObjectsCount + intialCount, userAccountService.getCount(new UserAccountFilter()));
	}

	
	@Test
	public void testFind() {
		UserAccountFilter filter = new UserAccountFilter();
		filter.setSortColumn("created");
		filter.setSortOrder(true);
		
		final int intialCount = userAccountService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}

		final List<IUserAccount> allEntities = userAccountService.find(filter);

		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getEMail());
			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getFirstName());
			assertNotNull(entityFromDb.getLastName());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	
	@Test
	public void testDelete() {
		final IUserAccount entity = saveNewUserAccount();

		assertNotNull(userAccountService.get(entity.getId()));

		userAccountService.delete(entity.getId());
		assertNull(userAccountService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewUserAccount();
		assertNotEquals(0, userAccountService.getAll().size());
		userAccountService.deleteAll();
		assertEquals(0, userAccountService.getAll().size());
	}
}
