package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;

public class CargoOrderTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ICargoOrder entity = saveNewCargoOrder();

		final ICargoOrder entityFromDb = cargoOrderService.get(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getCustomer());
		assertNotNull(entityFromDb.getStationFrom());
		assertNotNull(entityFromDb.getStationTo());
		assertNotNull(entityFromDb.getWeight());
		assertNotNull(entityFromDb.getCargoType());
		assertNotNull(entityFromDb.getDate());
		
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getCustomer().getId().equals(entity.getCustomer().getId()));
		assertTrue(entityFromDb.getStationFrom().getId().equals(entity.getStationFrom().getId()));
		assertTrue(entityFromDb.getStationTo().getId().equals(entity.getStationTo().getId()));
		
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICargoOrder entity = saveNewCargoOrder();

		Double newWeight = entity.getWeight() + 100.0;
		entity.setWeight(newWeight);
		Thread.sleep(2000);
		cargoOrderService.save(entity);

		final ICargoOrder entityFromDb = cargoOrderService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newWeight, entityFromDb.getWeight());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getCustomer());
		assertNotNull(entityFromDb.getStationFrom());
		assertNotNull(entityFromDb.getStationTo());
		assertNotNull(entityFromDb.getWeight());
		assertNotNull(entityFromDb.getCargoType());
		assertNotNull(entityFromDb.getDate());

		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertFalse(entity.getWeight().equals(entityFromDb.getWeight()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = cargoOrderService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCargoOrder();
		}

		final List<ICargoOrder> allEntities = cargoOrderService.getAll();

		for (final ICargoOrder entityFromDb : allEntities) {
			assertNotNull(entityFromDb);
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
			assertNotNull(entityFromDb.getCustomer());
			assertNotNull(entityFromDb.getStationFrom());
			assertNotNull(entityFromDb.getStationTo());
			assertNotNull(entityFromDb.getWeight());
			assertNotNull(entityFromDb.getCargoType());
			assertNotNull(entityFromDb.getDate());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICargoOrder entity = saveNewCargoOrder();

		assertNotNull(cargoOrderService.get(entity.getId()));

		cargoOrderService.delete(entity.getId());
		assertNull(cargoOrderService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCargoOrder();
		assertNotEquals(0, cargoOrderService.getAll().size());
		cargoOrderService.deleteAll();
		assertEquals(0, cargoOrderService.getAll().size());
	}
}
