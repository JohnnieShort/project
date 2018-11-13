package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CustomerFilter;

public class CustomerServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ICustomer entity = saveNewCustomer();

		final ICustomer entityFromDb = customerService.get(entity.getId());
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
	public void testgetFullInfo() {
		final ICustomer entity = saveNewCustomer();

		final ICustomer entityFromDb = customerService.getFullInfo(entity.getId());
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
		final ICustomer entity = saveNewCustomer();

		IUserAccount newUA = saveNewUserAccount();
		entity.setUserAccount(newUA);

		Thread.sleep(2000);
		customerService.save(entity);

		final ICustomer entityFromDb = customerService.get(entity.getId());

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
		final int intialCount = customerService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCustomer();
		}

		final List<ICustomer> allEntities = customerService.getAll();

		for (final ICustomer entityFromDb : allEntities) {
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
	public void testFind() {
		
		CustomerFilter filter = new CustomerFilter();
		filter.setFetchUserAccount(true);
		filter.setSortColumn("created");
		filter.setSortOrder(true);
		
		final int intialCount = customerService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCustomer();
		}

		final List<ICustomer> allEntities = customerService.find(filter);

		for (final ICustomer entityFromDb : allEntities) {
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
	public void testGetCount() {
		
		
		final int intialCount = customerService.find(new CustomerFilter()).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCustomer();
		}

		

		assertEquals(randomObjectsCount + intialCount, customerService.find(new CustomerFilter()).size());
	}

	@Test
	public void testDelete() {
		final ICustomer entity = saveNewCustomer();

		assertNotNull(customerService.get(entity.getId()));

		customerService.delete(entity.getId());
		assertNull(customerService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCustomer();
		assertNotEquals(0, customerService.getAll().size());
		customerService.deleteAll();
		assertEquals(0, customerService.getAll().size());
	}
}
