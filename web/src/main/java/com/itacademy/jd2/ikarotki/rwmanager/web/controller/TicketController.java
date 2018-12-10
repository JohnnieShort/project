package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TicketFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TicketToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TicketDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController extends AbstractController<TicketDTO> {
	private ITicketService ticketService;
	private TicketToDTOConverter toDtoConverter;
	private TicketFromDTOConverter fromDtoConverter;
	private IPassengerService passengerService;
	private IRouteItemService routeItemService;
	private IUserAccountService userAccountService;
	private IPassengerRouteService routeService;

	@Autowired
	private TicketController(ITicketService ticketService, TicketToDTOConverter toDtoConverter,
			TicketFromDTOConverter fromDtoConverter, IPassengerService passengerService,
			IRouteItemService routeItemService, IUserAccountService userAccountService,
			IPassengerRouteService routeService) {
		super();
		this.ticketService = ticketService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
		this.passengerService = passengerService;
		this.routeItemService = routeItemService;
		this.userAccountService = userAccountService;
		this.routeService = routeService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final TicketFilter filter = new TicketFilter();
		prepareFilter(gridState, filter);

		final List<ITicket> entities = ticketService.find(filter);
		List<TicketDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(ticketService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("ticket.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm(final HttpServletRequest req) {
		final Map<String, Object> hashMap = new HashMap<>();

		TicketDTO dto = new TicketDTO();
		hashMap.put("formModel", dto);
		String url = req.getHeader("referer");
		hashMap.put("url", url);
		addRoutes(hashMap);
		addPassengers(hashMap);

		return new ModelAndView("blankTicket.edit", hashMap);
	}

	private void addPassengers(Map<String, Object> hashMap) {
		PassengerFilter filter = new PassengerFilter();
		filter.setFetchUserAccount(true);
		List<IPassenger> passengers = passengerService.find(filter);
		Map<Integer, String> passengerChoices = passengers.stream().collect(
				Collectors.toMap(passenger -> passenger.getId(), passenger -> passenger.getUserAccount().getFirstName()
						+ " " + passenger.getUserAccount().getLastName()));
		hashMap.put("passengerChoices", passengerChoices);

	}

	private void addRoutes(Map<String, Object> hashMap) {
		List<IPassengerRoute> routes = routeService.findActual(new PassengerRouteFilter());
		Map<Integer, String> routeChoices = routes.stream().collect(Collectors.toMap(route -> route.getId(),
				route -> route.getId() + " " + route.getPassengerRouteType().toString()));
		hashMap.put("routeChoices", routeChoices);

	}

	@RequestMapping(value = "/{id}/buy", method = RequestMethod.GET)
	public ModelAndView showTicket(@PathVariable(name = "id", required = true) final Integer id,
			final HttpServletRequest req) {
		final Map<String, Object> hashMap = new HashMap<>();
		Integer passengerId = passengerService.getByUAId(AuthHelper.getLoggedUserId());
		hashMap.put("passengerId", passengerId);
		hashMap.put("passengerRouteId", id);
		TicketDTO dto = new TicketDTO();
		hashMap.put("formModel", dto);
		String url = req.getHeader("referer");
		hashMap.put("url", url);
		loadItems(hashMap, id);
		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final TicketDTO formModel, final BindingResult result,
			@RequestParam(name = "quantity", required = false) Integer quantity,
			@RequestParam(name = "street", required = false) String street,
			@RequestParam(name = "building", required = false) Integer building,
			@RequestParam(name = "apartments", required = false) Integer apartments,
			@RequestParam(name = "phone", required = false) String phone, final HttpServletRequest req) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			Integer passengerId = passengerService.getByUAId(AuthHelper.getLoggedUserId());
			hashMap.put("passengerId", passengerId);
			TicketDTO dto = new TicketDTO();
			hashMap.put("formModel", dto);
			String url = req.getHeader("referer");
			hashMap.put("url", url);
			hashMap.put("street", street);
			hashMap.put("building", building);
			hashMap.put("apartments", apartments);
			hashMap.put("phone", phone);
			hashMap.put("quantity", quantity);
			loadItems(hashMap, formModel.getPassengerRouteId());
			return new ModelAndView("ticket.edit", hashMap);
		} else {

			for (int i = 0; i < quantity; i++) {
				final ITicket entity = fromDtoConverter.apply(formModel);
				setPassenger(street, building, apartments, phone, entity);
				ticketService.save(entity);
			}
			return "redirect:/ticket";
		}
	}

	private void setPassenger(String street, Integer building, Integer apartments, String phone, final ITicket entity) {
		IPassenger passenger = passengerService.createEntity();
		Integer passengerId = passengerService.getByUAId(AuthHelper.getLoggedUserId());
		if (passengerId == null) {
			passenger.setUserAccount(userAccountService.get(AuthHelper.getLoggedUserId()));
			passenger.setStreet(street);
			passenger.setBuilding(building);
			passenger.setApartments(apartments);
			passenger.setPhone(phone);
			passengerService.save(passenger);
		}
		passengerId = passengerService.getByUAId(AuthHelper.getLoggedUserId());
		passenger = passengerService.get(passengerId);
		entity.setPassenger(passenger);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		ticketService.delete(id);
		return "redirect:/ticket";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITicket dbModel = ticketService.get(id);
		final TicketDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("ticket.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TicketDTO dto = toDtoConverter.apply(ticketService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("ticket.edit", hashMap);
	}

	private void loadItems(Map<String, Object> hashMap, Integer routeId) {
		List<IRouteItem> itemsList = new ArrayList<IRouteItem>();
		RouteItemFilter filter = new RouteItemFilter();
		filter.setFetchStationFrom(true);
		filter.setFetchStationTo(true);
		itemsList = routeItemService.getItems(routeId, filter);
		if (itemsList == null) {
			return;
		}
		Map<Integer, String> fromChoices = itemsList.stream().collect(Collectors.toMap(
				routeItem -> routeItem.getStationFrom().getId(), routeItem -> routeItem.getStationFrom().getName()));
		hashMap.put("fromItems", fromChoices);
		Map<Integer, String> toChoices = itemsList.stream().collect(Collectors
				.toMap(routeItem -> routeItem.getStationTo().getId(), routeItem -> routeItem.getStationTo().getName()));
		hashMap.put("toItems", toChoices);

	}

}
