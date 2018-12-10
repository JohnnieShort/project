package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerRouteToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.StationToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.StationDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.TicketDTO;

@Controller
@RequestMapping(value = "/ajax-samples")
public class AjaxSamplesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSamplesController.class);
	private IPassengerRouteService routeService;
	private PassengerRouteToDTOConverter routeToDTOConverter;
	private StationToDTOConverter stationToDTOConverter;
	private IRouteItemService routeItemService;

	@Autowired
	private AjaxSamplesController(IPassengerRouteService routeService,
			PassengerRouteToDTOConverter routeToDTOConverter, IRouteItemService routeItemService, StationToDTOConverter stationToDTOConverter) {
		this.routeService = routeService;
		this.routeToDTOConverter = routeToDTOConverter;
		this.routeItemService = routeItemService;
		this.stationToDTOConverter = stationToDTOConverter;
	}

	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public ResponseEntity<List<PassengerRouteDTO>> getRoutes() {
		final List<PassengerRouteDTO> routesDTO = new ArrayList<PassengerRouteDTO>();
		List<IPassengerRoute> routes = routeService.find(new PassengerRouteFilter());
		for (IPassengerRoute route : routes) {
			routesDTO.add(routeToDTOConverter.apply(route));
		}
		//String json = new Gson().toJson(routesDTO);
		return new ResponseEntity<List<PassengerRouteDTO>>(routesDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/fromStations", method = RequestMethod.GET)
	public ResponseEntity<List<StationDTO>> getStationsFrom(
			@RequestParam(name = "routeId", required = true) final Integer routeId) {
		final List<StationDTO> stationsFrom = new ArrayList<StationDTO>();
		RouteItemFilter filter = new RouteItemFilter();
		filter.setFetchStationFrom(true);
		List<IRouteItem> routeItems = routeItemService.getItems(routeId, filter);
		for(IRouteItem item: routeItems) {
			stationsFrom.add(stationToDTOConverter.apply(item.getStationFrom()));
		}
		return new ResponseEntity<List<StationDTO>>(stationsFrom, HttpStatus.OK);
	}

	@RequestMapping(value = "/toStations", method = RequestMethod.GET)
	public ResponseEntity<List<StationDTO>> getStationsTo(
			@RequestParam(name = "routeId", required = true) final Integer routeId) {
		final List<StationDTO> stationsTo = new ArrayList<StationDTO>();
		RouteItemFilter filter = new RouteItemFilter();
		filter.setFetchStationTo(true);
		List<IRouteItem> routeItems = routeItemService.getItems(routeId, filter);
		for(IRouteItem item: routeItems) {
			stationsTo.add(stationToDTOConverter.apply(item.getStationTo()));
		}
		return new ResponseEntity<List<StationDTO>>(stationsTo, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("ticketForm") final TicketDTO formModel, final BindingResult result) {
		LOGGER.info("FORM RECEIVED: {}", formModel);
		return "redirect:/ajax-samples";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPage() {
		return new ModelAndView("blankTicket.edit", "ticketForm", new TicketDTO());
	}

}