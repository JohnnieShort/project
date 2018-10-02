package com.itacademy.jd2.ikarotki.rwmanager.service;

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
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.CargoOrderServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.CargoRouteServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.CustomerServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.LocomotiveServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.PassengerRouteServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.PassengerServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.RouteItemServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.StationServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.TicketServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.TrainServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.UserAccountServiceImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.WagonServiceImpl;

@SpringJUnitConfig(locations = "classpath:service-context.xml")

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
		// clean DB recursive
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
		entity.setLongitude(getRANDOM().nextDouble() * 100);
		entity.setLatitude(getRANDOM().nextDouble() * 100);
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		stationService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setEMail("e-mail - " + getRandomPrefix());
		entity.setPassword("pass - " + getRandomPrefix());

		entity.setRole(Role.values()[getRANDOM().nextInt(3)]);
		entity.setFirstName("name - " + getRandomPrefix());
		entity.setLastName("last_name - " + getRandomPrefix());
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
		userAccountService.save(entity);
		return entity;
	}

	protected ILocomotive saveNewLocomotive() {
		final ILocomotive entity = locomotiveService.createEntity();
		entity.setName("locomotive - " + getRandomPrefix());
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);
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
		entity.setFreightPrice(10000.0 + getRANDOM().nextDouble());
		entity.setCapacity(10000.0 * getRANDOM().nextDouble());
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
		entity.setFrom(fromEntity);
		IStation toEntity;
		do {
			toEntity = saveNewStation();
		} while (fromEntity.getName().equals(toEntity.getName()));
		entity.setTo(toEntity);
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
		entity.setTime(20.0);
		entity.setOrdinalNum(getRANDOM().nextInt(100));
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		routeItemService.save(entity);
		return entity;
	}

	protected ICargoOrder saveNewCargoOrder() {
		final ICargoOrder entity = cargoOrderService.createEntity();
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
		entity.setWeight(getRANDOM().nextDouble() * 1000000);
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		cargoOrderService.save(entity);
		return entity;
	}

	protected ICargoRoute saveNewCargoRoute() {
		final ICargoRoute entity = cargoRouteService.createEntity();
		ICargoOrder COEntity = saveNewCargoOrder();
		entity.setCargoOrder(COEntity);
		ITrain trainEntity = saveNewTrain();
		entity.setTrain(trainEntity);
		entity.setPrice(getRANDOM().nextDouble() * 1000000);
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
		entity.setPrice(getRANDOM().nextDouble() * 100);
		IStation fromEntity = saveNewStation();
		entity.setFrom(fromEntity);
		IStation toEntity;
		do {
			toEntity = saveNewStation();
		} while (fromEntity.getName().equals(toEntity.getName()));
		entity.setTo(toEntity);
		Date date = new Date();
		entity.setCreated(date);
		entity.setUpdated(date);

		ticketService.save(entity);
		return entity;
	}

}
