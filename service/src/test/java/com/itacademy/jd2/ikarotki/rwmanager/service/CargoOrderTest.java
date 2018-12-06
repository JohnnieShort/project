package com.itacademy.jd2.ikarotki.rwmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoOrderFilter;

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
		assertNotNull(entityFromDb.getCargoRoute());
		assertNotNull(entityFromDb.getPrice());
		assertNotNull(entityFromDb.getDate());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		assertTrue(entityFromDb.getCustomer().getId().equals(entity.getCustomer().getId()));
		assertTrue(entityFromDb.getStationFrom().getId().equals(entity.getStationFrom().getId()));
		assertTrue(entityFromDb.getCargoRoute().getId().equals(entity.getCargoRoute().getId()));
		assertTrue(entityFromDb.getStationTo().getId().equals(entity.getStationTo().getId()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ICargoOrder entity = saveNewCargoOrder();

		Double newWeight = entity.getWeight() + 100.0;
		entity.setWeight(newWeight);
		Double newPrice = entity.getPrice() + 100.0;
		entity.setPrice(newPrice);
		Thread.sleep(2000);
		cargoOrderService.save(entity);

		final ICargoOrder entityFromDb = cargoOrderService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newWeight, entityFromDb.getWeight());
		assertEquals(newPrice, entityFromDb.getPrice());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getCustomer());
		assertNotNull(entityFromDb.getStationFrom());
		assertNotNull(entityFromDb.getStationTo());
		assertNotNull(entityFromDb.getWeight());
		assertNotNull(entityFromDb.getCargoType());
		assertNotNull(entityFromDb.getCargoRoute());
		assertNotNull(entityFromDb.getDate());

		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
		assertTrue(entity.getWeight().equals(entityFromDb.getWeight()));
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
			assertNotNull(entityFromDb.getCargoRoute());
			assertNotNull(entityFromDb.getDate());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testFind() {
		CargoOrderFilter filter = new CargoOrderFilter();
		filter.setFetchCargoRoute(true);
		filter.setFetchCustomer(true);
		filter.setFetchStationFrom(true);
		filter.setFetchStationTo(true);
		filter.setSortColumn("created");
		filter.setSortOrder(true);
		
		final int intialCount = cargoOrderService.find(filter).size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCargoOrder();
		}

		final List<ICargoOrder> allEntities = cargoOrderService.find(filter);

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
			assertNotNull(entityFromDb.getCargoRoute());
			assertNotNull(entityFromDb.getDate());
			assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
			
			assertNotNull(entityFromDb.getCargoRoute().getId());
			assertNotNull(entityFromDb.getCargoRoute().getTrain());
			assertNotNull(entityFromDb.getCargoRoute().getCreated());
			assertNotNull(entityFromDb.getCargoRoute().getUpdated());
			
			assertNotNull(entityFromDb.getCustomer().getId());
			assertNotNull(entityFromDb.getCustomer().getUserAccount());
			assertNotNull(entityFromDb.getCustomer().getCreated());
			assertNotNull(entityFromDb.getCustomer().getUpdated());
			
			assertNotNull(entityFromDb.getStationFrom());
			assertNotNull(entityFromDb.getStationFrom().getId());
			assertNotNull(entityFromDb.getStationFrom().getName());
			assertNotNull(entityFromDb.getStationFrom().getLatitude());
			assertNotNull(entityFromDb.getStationFrom().getLongitude());
			assertNotNull(entityFromDb.getStationFrom().getCreated());
			assertNotNull(entityFromDb.getStationFrom().getUpdated());
			
			assertNotNull(entityFromDb.getStationTo());
			assertNotNull(entityFromDb.getStationTo().getId());
			assertNotNull(entityFromDb.getStationTo().getName());
			assertNotNull(entityFromDb.getStationTo().getLatitude());
			assertNotNull(entityFromDb.getStationTo().getLongitude());
			assertNotNull(entityFromDb.getStationTo().getCreated());
			assertNotNull(entityFromDb.getStationTo().getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	
	@Test
	public void testGetCount() {
		final long intialCount = cargoOrderService.getCount(new CargoOrderFilter());

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCargoOrder();
		}

		

		assertEquals(randomObjectsCount + intialCount, cargoOrderService.getCount(new CargoOrderFilter()));
	}
	
	@Test
	public void testgetFullInfo() {
		final ICargoOrder entity = saveNewCargoOrder();

		final ICargoOrder entityFromDb = cargoOrderService.getFullInfo(entity.getId());
		assertNotNull(entity);
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertNotNull(entityFromDb.getCustomer());
		
		assertNotNull(entityFromDb.getWeight());
		assertNotNull(entityFromDb.getCargoType());
		assertNotNull(entityFromDb.getCargoRoute());
		assertNotNull(entityFromDb.getPrice());
		assertNotNull(entityFromDb.getDate());

		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
		
		assertTrue(entityFromDb.getCustomer().getId().equals(entity.getCustomer().getId()));
		assertNotNull(entityFromDb.getCustomer().getUserAccount());
		assertNotNull(entityFromDb.getCustomer().getCreated());
		assertNotNull(entityFromDb.getCustomer().getUpdated());
		
		assertNotNull(entityFromDb.getStationFrom());
		assertNotNull(entityFromDb.getStationFrom().getId());
		assertNotNull(entityFromDb.getStationFrom().getName());
		assertNotNull(entityFromDb.getStationFrom().getLatitude());
		assertNotNull(entityFromDb.getStationFrom().getLongitude());
		assertNotNull(entityFromDb.getStationFrom().getCreated());
		assertNotNull(entityFromDb.getStationFrom().getUpdated());
		
		assertNotNull(entityFromDb.getStationTo());
		assertNotNull(entityFromDb.getStationTo().getId());
		assertNotNull(entityFromDb.getStationTo().getName());
		assertNotNull(entityFromDb.getStationTo().getLatitude());
		assertNotNull(entityFromDb.getStationTo().getLongitude());
		assertNotNull(entityFromDb.getStationTo().getCreated());
		assertNotNull(entityFromDb.getStationTo().getUpdated());
		
		
		assertTrue(entityFromDb.getCargoRoute().getId().equals(entity.getCargoRoute().getId()));
		assertNotNull(entityFromDb.getCargoRoute().getTrain());
		assertNotNull(entityFromDb.getCargoRoute().getCreated());
		assertNotNull(entityFromDb.getCargoRoute().getUpdated());
		
		
		
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
