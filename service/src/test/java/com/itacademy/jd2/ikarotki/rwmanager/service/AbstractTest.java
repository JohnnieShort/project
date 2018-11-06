package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")

public abstract class AbstractTest {
	@Autowired
	protected ICargoOrderService cargoOrderService;
	@Autowired
	protected ICargoRouteService cargoRouteService;
	@Autowired
	protected ICustomerService customerService;
	@Autowired
	protected ILocomotiveService locomotiveService;
	@Autowired
	protected IPassengerRouteService passengerRouteService;
	@Autowired
	protected IPassengerService passengerService;
	@Autowired
	protected IRouteItemService routeItemService;
	@Autowired
	protected IStationService stationService;
	@Autowired
	protected ITicketService ticketService;
	@Autowired
	protected ITrainService trainService;
	@Autowired
	protected IUserAccountService userAccountService;
	@Autowired
	protected IWagonService wagonService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		
		cargoOrderService.deleteAll();
		cargoRouteService.deleteAll();
		customerService.deleteAll();
		locomotiveService.deleteAll();
		passengerRouteService.deleteAll();
		passengerService.deleteAll();
		routeItemService.deleteAll();
		stationService.deleteAll();
		ticketService.deleteAll();
		trainService.deleteAll();
		userAccountService.deleteAll();
		wagonService.deleteAll();

	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected IStation saveNewStation() {
		final IStation entity = stationService.createEntity();
		entity.setName("station - " + getRandomPrefix());
		entity.setLongitude(new BigDecimal(getRANDOM().nextDouble() * 100).setScale(2, RoundingMode.UP).doubleValue());
		entity.setLatitude(new BigDecimal(getRANDOM().nextDouble() * 100).setScale(2, RoundingMode.UP).doubleValue());
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		stationService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setEMail("e-mail - " + getRandomPrefix());
		String pass = "pass - " + getRandomPrefix();
		entity.setPassword(pass);

		entity.setRole(Role.values()[getRANDOM().nextInt(3)]);
		entity.setFirstName("name - " + getRandomPrefix());
		entity.setLastName("last_name - " + getRandomPrefix());
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		userAccountService.save(entity);
		entity.setPassword(pass);
		return entity;
	}

	protected ILocomotive saveNewLocomotive() {
		final ILocomotive entity = locomotiveService.createEntity();
		entity.setName("locomotive - " + getRandomPrefix());
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		entity.setPower(new BigDecimal(10000.0 * getRANDOM().nextDouble()).setScale(2, RoundingMode.UP).doubleValue());
		locomotiveService.save(entity);
		return entity;
	}

	protected ITrain saveNewTrain() {
		final ITrain entity = trainService.createEntity();
		ILocomotive locEntity = saveNewLocomotive();
		entity.setLocomotive(locEntity);
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		trainService.save(entity);
		return entity;
	}

	protected IWagon saveNewWagon() {
		final IWagon entity = wagonService.createEntity();
		entity.setWagonType(WagonType.values()[getRANDOM().nextInt(2)]);
		ITrain trainEntity = saveNewTrain();
		entity.setTrain(trainEntity);
		entity.setFreightPrice(new BigDecimal(10000.0 * getRANDOM().nextDouble()).setScale(2, RoundingMode.UP).doubleValue());
		entity.setCapacity(new BigDecimal(10000.0 * getRANDOM().nextDouble()).setScale(2, RoundingMode.UP).doubleValue());
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		wagonService.save(entity);
		return entity;
	}

	protected IPassenger saveNewPassenger() {
		final IPassenger entity = passengerService.createEntity();
		IUserAccount uAEntity = saveNewUserAccount();
		entity.setUserAccount(uAEntity);
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		passengerService.save(entity);
		return entity;
	}

	protected IPassengerRoute saveNewPassengerRoute() {
		final IPassengerRoute entity = passengerRouteService.createEntity();
		IStation fromEntity = saveNewStation();
		entity.setStationFrom(fromEntity);
		IStation toEntity;
		do {
			toEntity = saveNewStation();
		} while (fromEntity.getName().equals(toEntity.getName()));
		entity.setStationTo(toEntity);
		Date departure = new Date();
		entity.setDeparture(departure);
		entity.setArrival(departure);
		entity.setPassengerRoutetype(PassengerRouteType.values()[getRANDOM().nextInt(2)]);
		ITrain trainEntity = saveNewTrain();
		entity.setTrain(trainEntity);
		entity.setIsActual(true);
		entity.setFrequency(Frequency.values()[getRANDOM().nextInt(2)]);
		entity.setPlaces(getRANDOM().nextInt(200));
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		passengerRouteService.save(entity);
		return entity;
	}

	protected ICustomer saveNewCustomer() {
		final ICustomer entity = customerService.createEntity();
		IUserAccount UAEntity = saveNewUserAccount();
		entity.setUserAccount(UAEntity);
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		customerService.save(entity);
		return entity;
	}

	protected IRouteItem saveNewRouteItem() {
		final IRouteItem entity = routeItemService.createEntity();
		IPassengerRoute PREntity = saveNewPassengerRoute();
		entity.setPassengerRoute(PREntity);
		IStation fromEntity = saveNewStation();
		entity.setStationFrom(fromEntity);
		IStation toEntity;
		do {
			toEntity = saveNewStation();
		} while (fromEntity.getName().equals(toEntity.getName()));
		entity.setStationTo(toEntity);
		Date date = new Date();
		entity.setArrival(date);
		date = new Date();
		entity.setDeparture(date);
		entity.setOrdinalNum(getRANDOM().nextInt(100));
		date = new Date();
		entity.setCreated(date);

		entity.setUpdated(date);
		entity.setIsFirst(false);
		entity.setIsLast(false);

		routeItemService.save(entity);
		return entity;
	}

	protected ICargoOrder saveNewCargoOrder() {
		final ICargoOrder entity = cargoOrderService.createEntity();
		ICargoRoute CREntity = saveNewCargoRoute();
		entity.setCargoRoute(CREntity);
		ICustomer customerEntity = saveNewCustomer();
		entity.setCustomer(customerEntity);
		entity.setCargoType(CargoType.values()[getRANDOM().nextInt(2)]);
		IStation fromEntity = saveNewStation();
		entity.setStationFrom(fromEntity);
		IStation toEntity;
		do {
			toEntity = saveNewStation();
		} while (fromEntity.getName().equals(toEntity.getName()));
		entity.setStationTo(toEntity);
		Date dateArrival = new Date();
		entity.setDate(dateArrival);
		entity.setWeight(new BigDecimal(getRANDOM().nextDouble() * 1000000).setScale(2, RoundingMode.UP).doubleValue());
		entity.setPrice(new BigDecimal(10000.0 * getRANDOM().nextDouble()).setScale(2, RoundingMode.UP).doubleValue());
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		cargoOrderService.save(entity);
		return entity;
	}

	protected ICargoRoute saveNewCargoRoute() {
		final ICargoRoute entity = cargoRouteService.createEntity();

		ITrain trainEntity = saveNewTrain();
		entity.setTrain(trainEntity);
		
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		cargoRouteService.save(entity);
		return entity;
	}

	protected ITicket saveNewTicket() {
		final ITicket entity = ticketService.createEntity();
		IPassenger passengerEntity = saveNewPassenger();
		entity.setPassenger(passengerEntity);
		IPassengerRoute PREntity = saveNewPassengerRoute();
		entity.setPassengerRoute(PREntity);
		entity.setPrice(new BigDecimal(getRANDOM().nextDouble() * 100).setScale(2, RoundingMode.UP).doubleValue());
		IStation fromEntity = saveNewStation();
		entity.setStationFrom(fromEntity);
		IStation toEntity;
		do {
			toEntity = saveNewStation();
		} while (fromEntity.getName().equals(toEntity.getName()));
		entity.setStationTo(toEntity);
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		ticketService.save(entity);
		return entity;
	}

}
