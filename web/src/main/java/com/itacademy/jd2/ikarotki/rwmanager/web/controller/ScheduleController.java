package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IStationService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITrainService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IWagonService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerRouteFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerRouteToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.RouteItemFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.StationToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController extends AbstractController<PassengerRouteDTO>{
	private IPassengerRouteService passengerRouteService;
	private ITrainService trainService;
	private IWagonService wagonService;
	private IRouteItemService routeItemService;
	private PassengerRouteToDTOConverter toDtoConverter;
	@Autowired
	private ScheduleController(IPassengerRouteService pasengerRouteService, IStationService stationService,
			ITrainService trainService, IWagonService wagonService, IRouteItemService routeItemService,
			PassengerRouteToDTOConverter toDtoConverter, PassengerRouteFromDTOConverter fromDtoConverter,
			StationToDTOConverter stationToDtoConverter, RouteItemFromDTOConverter routeItemFromDTOConverter) {
		super();
		this.passengerRouteService = pasengerRouteService;
		this.routeItemService = routeItemService;
		this.trainService = trainService;
		this.toDtoConverter = toDtoConverter;
		this.wagonService = wagonService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showSchedule(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PassengerRouteFilter filter = new PassengerRouteFilter();
		prepareFilter(gridState, filter);

		final List<IPassengerRoute> entities = passengerRouteService.findActual(filter);
		List<PassengerRouteDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(passengerRouteService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);

		List<ITrain> trains = new ArrayList<ITrain>();
		trains = trainService.find(new TrainFilter());
		Map<Integer, Double> places = wagonService.getPlaces(trains);
		models.put("places", places);
		
		
		RouteItemFilter routeItemFilter = new RouteItemFilter();
		routeItemFilter.setFetchStationFrom(true);
		routeItemFilter.setFetchStationTo(true);
		Map<Integer, String> firstStationsNames = routeItemService.getStationsNames(entities, routeItemFilter, true);
		models.put("firstStations", firstStationsNames);
		Map<Integer, String> lastStationsNames = routeItemService.getStationsNames(entities, routeItemFilter, false);
		models.put("lastStations", lastStationsNames);
		Map<Integer, Date> departures = routeItemService.getTime(entities, routeItemFilter, true);
		models.put("departures", departures);
		Map<Integer, Date> arrivals = routeItemService.getTime(entities, routeItemFilter, false);
		models.put("arrivals", arrivals);
		return new ModelAndView("schedule", models);
	}
}
