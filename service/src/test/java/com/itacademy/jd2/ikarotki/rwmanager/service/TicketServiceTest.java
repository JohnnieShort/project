package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;

public class TicketServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ITicket entity = saveNewTicket();

		final ITicket entityFromDb = ticketService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getPassenger());
		assertNotNull(entityFromDb.getPassengerRoute());
		assertNotNull(entityFromDb.getPrice());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getPassenger().getId().equals(entity.getPassenger().getId()));
		assertTrue(entityFromDb.getPassengerRoute().getId().equals(entity.getPassengerRoute().getId()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ITicket entity = saveNewTicket();

		Double newPrice = entity.getPrice() + 100.0;
		entity.setPrice(newPrice);
		Thread.sleep(2000);
		ticketService.save(entity);

		final ITicket entityFromDb = ticketService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newPrice, entityFromDb.getPrice());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertTrue(entity.getPrice().equals(entityFromDb.getPrice()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = ticketService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTicket();
		}

		final List<ITicket> allEntities = ticketService.getAll();

		for (final ITicket entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getPassenger());
			assertNotNull(entityFromDb.getPassengerRoute());
			assertNotNull(entityFromDb.getPrice());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ITicket entity = saveNewTicket();

		assertNotNull(ticketService.get(entity.getId()));

		ticketService.delete(entity.getId());
		assertNull(ticketService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTicket();
		assertNotEquals(0, ticketService.getAll().size());
		ticketService.deleteAll();
		assertEquals(0, ticketService.getAll().size());
	}
}
