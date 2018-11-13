package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;

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
	public void testGetFullInfo() {
		final ITicket entity = saveNewTicket();

		final ITicket entityFromDb = ticketService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getStationFrom());
		assertNotNull(entityFromDb.getStationTo());
		assertNotNull(entityFromDb.getPassenger());
		assertNotNull(entityFromDb.getPassengerRoute());
		assertNotNull(entityFromDb.getPrice());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		assertTrue(entityFromDb.getPassenger().getId().equals(entity.getPassenger().getId()));
		assertTrue(entityFromDb.getPassenger().getUserAccount().getId()
				.equals(entity.getPassenger().getUserAccount().getId()));
		assertNotNull(entityFromDb.getPassenger().getCreated());
		assertNotNull(entityFromDb.getPassenger().getUpdated());

		assertTrue(entityFromDb.getPassengerRoute().getId().equals(entity.getPassengerRoute().getId()));
		assertTrue(entityFromDb.getPassengerRoute().getFrequency().equals(entity.getPassengerRoute().getFrequency()));
		assertTrue(entityFromDb.getPassengerRoute().getIsActual().equals(entity.getPassengerRoute().getIsActual()));
		assertTrue(entityFromDb.getPassengerRoute().getPassengerRouteType()
				.equals(entity.getPassengerRoute().getPassengerRouteType()));
		assertTrue(entityFromDb.getPassengerRoute().getPlaces().equals(entity.getPassengerRoute().getPlaces()));
		assertNotNull(entityFromDb.getPassengerRoute().getArrival());
		assertNotNull(entityFromDb.getPassengerRoute().getDeparture());
		assertNotNull(entityFromDb.getPassengerRoute().getCreated());
		assertNotNull(entityFromDb.getPassengerRoute().getUpdated());

		assertNotNull(entityFromDb.getStationFrom().getCreated());
		assertNotNull(entityFromDb.getStationFrom().getUpdated());
		assertTrue(entityFromDb.getStationFrom().getName().equals(entity.getStationFrom().getName()));
		assertTrue(entityFromDb.getStationFrom().getLatitude().equals(entity.getStationFrom().getLatitude()));
		assertTrue(entityFromDb.getStationFrom().getLongitude().equals(entity.getStationFrom().getLongitude()));

		assertNotNull(entityFromDb.getStationTo().getCreated());
		assertNotNull(entityFromDb.getStationTo().getUpdated());
		assertTrue(entityFromDb.getStationTo().getName().equals(entity.getStationTo().getName()));
		assertTrue(entityFromDb.getStationTo().getLatitude().equals(entity.getStationTo().getLatitude()));
		assertTrue(entityFromDb.getStationTo().getLongitude().equals(entity.getStationTo().getLongitude()));

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
	public void testGetCount() {
		final long intialCount = ticketService.getCount(new TicketFilter());

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTicket();
		}

		assertEquals(randomObjectsCount + intialCount, ticketService.getCount(new TicketFilter()));
	}

	@Test
	public void testFind() {
		TicketFilter filter = new TicketFilter();
		filter.setFetchPassenger(true);
		filter.setFetchPassengerRoute(true);
		filter.setFetchStationFrom(true);
		filter.setFetchStationTo(true);
		filter.setSortColumn("created");
		filter.setSortOrder(true);

		final int intialCount = ticketService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTicket();
		}

		final List<ITicket> allEntities = ticketService.find(filter);

		for (final ITicket entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getStationFrom());
			assertNotNull(entityFromDb.getStationTo());
			assertNotNull(entityFromDb.getPassenger());
			assertNotNull(entityFromDb.getPassengerRoute());
			assertNotNull(entityFromDb.getPrice());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

			assertNotNull(entityFromDb.getStationFrom().getId());
			assertNotNull(entityFromDb.getStationFrom().getName());
			assertNotNull(entityFromDb.getStationFrom().getLatitude());
			assertNotNull(entityFromDb.getStationFrom().getLongitude());
			assertNotNull(entityFromDb.getStationFrom().getCreated());
			assertNotNull(entityFromDb.getStationFrom().getUpdated());

			assertNotNull(entityFromDb.getStationTo().getId());
			assertNotNull(entityFromDb.getStationTo().getName());
			assertNotNull(entityFromDb.getStationTo().getLatitude());
			assertNotNull(entityFromDb.getStationTo().getLongitude());
			assertNotNull(entityFromDb.getStationTo().getCreated());
			assertNotNull(entityFromDb.getStationTo().getUpdated());

			assertNotNull(entityFromDb.getPassenger().getId());
			assertNotNull(entityFromDb.getPassenger().getUserAccount());
			assertNotNull(entityFromDb.getPassenger().getUpdated());
			assertNotNull(entityFromDb.getPassenger().getCreated());

			assertNotNull(entityFromDb.getPassengerRoute().getId());
			assertNotNull(entityFromDb.getPassengerRoute().getArrival());
			assertNotNull(entityFromDb.getPassengerRoute().getDeparture());
			assertNotNull(entityFromDb.getPassengerRoute().getCreated());
			assertNotNull(entityFromDb.getPassengerRoute().getUpdated());
			assertNotNull(entityFromDb.getPassengerRoute().getFrequency());
			assertNotNull(entityFromDb.getPassengerRoute().getIsActual());
			assertNotNull(entityFromDb.getPassengerRoute().getPlaces());
			assertNotNull(entityFromDb.getPassengerRoute().getStationFrom());
			assertNotNull(entityFromDb.getPassengerRoute().getStationTo());
			assertNotNull(entityFromDb.getPassengerRoute().getTrain());
			assertNotNull(entityFromDb.getPassengerRoute().getPassengerRouteType());

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
