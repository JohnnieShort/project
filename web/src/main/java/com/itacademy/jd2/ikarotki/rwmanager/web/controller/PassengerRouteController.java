package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.google.gson.Gson;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.StationFilter;
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
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.RouteItemDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.list.GridStateDTO;

@Controller
@RequestMapping(value = "/passengerRoute")
public class PassengerRouteController extends AbstractController<PassengerRouteDTO> {
	private IPassengerRouteService passengerRouteService;
	private IStationService stationService;
	private ITrainService trainService;
	private IWagonService wagonService;
	private IRouteItemService routeItemService;
	private RouteItemFromDTOConverter routeItemFromDTOConverter;
	private PassengerRouteToDTOConverter toDtoConverter;
	private PassengerRouteFromDTOConverter fromDtoConverter;

	@Autowired
	private PassengerRouteController(IPassengerRouteService pasengerRouteService, IStationService stationService,
			ITrainService trainService, IWagonService wagonService, IRouteItemService routeItemService,
			PassengerRouteToDTOConverter toDtoConverter, PassengerRouteFromDTOConverter fromDtoConverter,
			StationToDTOConverter stationToDtoConverter, RouteItemFromDTOConverter routeItemFromDTOConverter) {
		super();
		this.passengerRouteService = pasengerRouteService;
		this.routeItemService = routeItemService;
		this.routeItemFromDTOConverter = routeItemFromDTOConverter;
		this.trainService = trainService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
		this.stationService = stationService;
		this.wagonService = wagonService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final PassengerRouteFilter filter = new PassengerRouteFilter();
		prepareFilter(gridState, filter);

		final List<IPassengerRoute> entities = passengerRouteService.find(filter);
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
		return new ModelAndView("passengerRoute.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		PassengerRouteDTO dto = new PassengerRouteDTO();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("passengerRoute.edit", hashMap);
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ModelAndView showStForm(@Valid @ModelAttribute("formModel") final PassengerRouteDTO formModel,
			final BindingResult result) {

		save(formModel, result);
		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("passengerRouteFormModelId", formModel.getId());
		RouteItemDTO routeItemDTO = new RouteItemDTO();

		hashMap.put("routeItemFormModel", routeItemDTO);
		loadStations(hashMap);
		loadItems(hashMap, formModel.getId());
		return new ModelAndView("stations.edit", hashMap);
	}

	@RequestMapping(value = "/saveItem", method = RequestMethod.POST)
	public ModelAndView saveRouteItem(@Valid @ModelAttribute("formModel") final RouteItemDTO formModel,
			final BindingResult result) {
		final Map<String, Object> hashMap = new HashMap<>();
		if (result.hasErrors()) {
			hashMap.put("passengerRouteFormModelId", formModel.getPassengerRouteId());
			RouteItemDTO routeItemDTO = new RouteItemDTO();
			hashMap.put("routeItemFormModel", routeItemDTO);
			loadStations(hashMap);

			loadItems(hashMap, formModel.getPassengerRouteId());
			return new ModelAndView("stations.edit", hashMap);
		} else {
			final IRouteItem entity = routeItemFromDTOConverter.apply(formModel);
			routeItemService.save(entity);
			hashMap.put("passengerRouteFormModelId", formModel.getPassengerRouteId());
			RouteItemDTO routeItemDTO = new RouteItemDTO();

			hashMap.put("routeItemFormModel", routeItemDTO);
			loadStations(hashMap);
			loadItems(hashMap, formModel.getPassengerRouteId());
			return new ModelAndView("stations.edit", hashMap);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final PassengerRouteDTO formModel,
			final BindingResult result) {
		if (result.hasErrors()) {
			return "passengerRoute.edit";
		} else {
			final IPassengerRoute entity = fromDtoConverter.apply(formModel);
			passengerRouteService.save(entity);
			return "redirect:/passengerRoute";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		passengerRouteService.delete(id);
		return "redirect:/passengerRoute";
	}

	@RequestMapping(value = "/{id}/details", method = RequestMethod.GET)
	public ModelAndView viewDetailsFromSchedule(@PathVariable(name = "id", required = true) final Integer id) {
		final IPassengerRoute dbModel = passengerRouteService.getFullInfo(id);
		final PassengerRouteDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		loadItems(hashMap, id);
		return new ModelAndView("route.details", hashMap);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPassengerRoute dbModel = passengerRouteService.getFullInfo(id);
		final PassengerRouteDTO dto = toDtoConverter.apply(dbModel);
		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		loadItems(hashMap, id);
		return new ModelAndView("passengerRoute.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PassengerRouteDTO dto = toDtoConverter.apply(passengerRouteService.get(id));

		final HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);

		return new ModelAndView("passengerRoute.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		List<PassengerRouteType> passengerRouteTypes = new ArrayList<PassengerRouteType>();
		passengerRouteTypes = Arrays.asList(PassengerRouteType.values());
		hashMap.put("passengerRouteTypes", passengerRouteTypes);
		List<Frequency> frequency = Arrays.asList(Frequency.values());
		hashMap.put("frequency", frequency);

		final List<ITrain> trains = trainService.find(new TrainFilter());
		final Map<Integer, String> trainChoices = trains.stream().collect(Collectors.toMap(ITrain::getId, train -> {
			return train.getId() + " " + train.getTrainType().name();
		}));

		hashMap.put("trainsChoices", trainChoices);

	}

	private void loadStations(final Map<String, Object> hashMap) {
		List<IStation> stationsList = stationService.find(new StationFilter());
		Map<Integer, String> stationChoices = stationsList.stream()
				.collect(Collectors.toMap(IStation::getId, station -> station.getName()));
		hashMap.put("stationChoices", stationChoices);

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
		Map<Integer, String> itemChoices = itemsList.stream().collect(Collectors.toMap(IRouteItem::getId, routeItem -> {
			return routeItem.getStationFrom().getName() + " -> " + routeItem.getStationTo().getName();
		}));
		hashMap.put("routeItems", itemChoices);
		loadPoints(hashMap, itemsList);

	}

	private void loadPoints(Map<String, Object> hashMap, List<IRouteItem> itemsList) {
		if (itemsList.size() > 0) {
			double[][] points = new double[itemsList.size() + 1][2];
			double longSum = 0;
			double latSum = 0;
			for (int i = 0; i < itemsList.size(); i++) {
				points[i][0] = itemsList.get(i).getStationFrom().getLatitude();
				points[i][1] = itemsList.get(i).getStationFrom().getLongitude();
				longSum += itemsList.get(i).getStationFrom().getLongitude();
				latSum += itemsList.get(i).getStationFrom().getLatitude();
			}
			points[points.length - 1][0] = itemsList.get(itemsList.size() - 1).getStationTo().getLatitude();
			points[points.length - 1][1] = itemsList.get(itemsList.size() - 1).getStationTo().getLongitude();
			longSum += itemsList.get(itemsList.size() - 1).getStationFrom().getLongitude();
			latSum += itemsList.get(itemsList.size() - 1).getStationFrom().getLatitude();
			double longAvg = 0;
			double latAvg = 0;
			longAvg = longSum / points.length;
			latAvg = latSum / points.length;
			hashMap.put("points", new Gson().toJson(points));
			hashMap.put("avgLat", latAvg);
			hashMap.put("avgLong", longAvg);
		}
	}

}
