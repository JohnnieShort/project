package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.RouteItemFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerRouteService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerRouteToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.StationToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TicketFromDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.TicketToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerRouteDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.StationDTO;
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

    private PassengerRouteToDTOConverter routeToDTOConverter;

    @Autowired
    private PassengerFromDTOConverter passengerFromDTOConverter;

    @Autowired
    private PassengerToDTOConverter passengerToDTOConverter;

    private StationToDTOConverter stationToDTOConverter;

    @Autowired
    private TicketController(ITicketService ticketService, TicketToDTOConverter toDtoConverter,
            TicketFromDTOConverter fromDtoConverter, IPassengerService passengerService,
            IRouteItemService routeItemService, IUserAccountService userAccountService,
            IPassengerRouteService routeService, PassengerRouteToDTOConverter routeToDTOConverter,
            StationToDTOConverter stationToDTOConverter) {
        super();
        this.ticketService = ticketService;
        this.toDtoConverter = toDtoConverter;
        this.fromDtoConverter = fromDtoConverter;
        this.passengerService = passengerService;
        this.routeItemService = routeItemService;
        this.userAccountService = userAccountService;
        this.routeService = routeService;
        this.routeToDTOConverter = routeToDTOConverter;

        this.stationToDTOConverter = stationToDTOConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(final HttpServletRequest req,
            @RequestParam(name = "page", required = false) final Integer pageNumber,
            @RequestParam(name = "sort", required = false) final String sortColumn) {

        final GridStateDTO gridState = getListDTO(req);
        gridState.setPage(pageNumber);
        gridState.setSort(sortColumn, "id");

        final TicketFilter filter = new TicketFilter();
        filter.setFetchPassenger(true);
        filter.setFetchPassengerRoute(true);

        prepareFilter(gridState, filter);

        final List<ITicket> entities = ticketService.find(filter);
        List<TicketDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
        gridState.setTotalCount(ticketService.getCount(filter));

        final Map<String, Object> models = new HashMap<>();
        models.put("gridItems", dtos);
        return new ModelAndView("ticket.list", models);
    }

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public ResponseEntity<List<PassengerRouteDTO>> getRoutes() {
        final List<PassengerRouteDTO> routesDTO = new ArrayList<PassengerRouteDTO>();
        List<IPassengerRoute> routes = routeService.find(new PassengerRouteFilter());
        for (IPassengerRoute route : routes) {
            routesDTO.add(routeToDTOConverter.apply(route));
        }
        // String json = new Gson().toJson(routesDTO);
        return new ResponseEntity<List<PassengerRouteDTO>>(routesDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/fromStations", method = RequestMethod.GET)
    public ResponseEntity<List<StationDTO>> getStationsFrom(
            @RequestParam(name = "routeId", required = true) final Integer routeId) {
        final List<StationDTO> stationsFrom = new ArrayList<StationDTO>();
        RouteItemFilter filter = new RouteItemFilter();
        filter.setFetchStationFrom(true);
        List<IRouteItem> routeItems = routeItemService.getItems(routeId, filter);
        for (IRouteItem item : routeItems) {
            stationsFrom.add(stationToDTOConverter.apply(item.getStationFrom()));
        }
        return new ResponseEntity<List<StationDTO>>(stationsFrom, HttpStatus.OK);
    }

    @RequestMapping(value = "/toStations", method = RequestMethod.GET)
    public ResponseEntity<List<StationDTO>> getStationsTo(
            @RequestParam(name = "routeId", required = true) final Integer routeId,
            @RequestParam(name = "selectedName", required = true) final Integer selectedName) {
        final List<StationDTO> stationsTo = new ArrayList<StationDTO>();
        RouteItemFilter filter = new RouteItemFilter();
        filter.setFetchStationTo(true);
        List<IRouteItem> routeItems = routeItemService.getItems(routeId, filter);

        for (IRouteItem item : routeItems) {
            if (item.getStationFrom().getId() >= selectedName) {
                stationsTo.add(stationToDTOConverter.apply(item.getStationTo()));
            }
        }
        return new ResponseEntity<List<StationDTO>>(stationsTo, HttpStatus.OK);
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
        Map<Integer, String> passengerChoices = passengers.stream()
                .collect(Collectors.toMap(IPassenger::getId, passenger -> passenger.getUserAccount().getFirstName()
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
        IPassenger iPassenger = passengerService.get(AuthHelper.getLoggedUserId());
        TicketDTO dto = new TicketDTO();
        if (iPassenger != null) {
            hashMap.put("passengerId", iPassenger.getId());
            dto.setPassenger(passengerToDTOConverter.apply(iPassenger));
        }

        hashMap.put("passengerRouteId", id);

        hashMap.put("formModel", dto);
        String url = req.getHeader("referer");
        hashMap.put("url", url);
        loadFromToItems(hashMap, id);
        return new ModelAndView("ticket.edit", hashMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@Valid @ModelAttribute("formModel") final TicketDTO formModel, final BindingResult result,
            @RequestParam(name = "quantity", required = false) Integer quantity, final HttpServletRequest req) {
        if (result.hasErrors()) {
            final Map<String, Object> hashMap = new HashMap<>();
            IPassenger iPassenger = passengerService.get(AuthHelper.getLoggedUserId());
            hashMap.put("passengerId", iPassenger == null ? null : iPassenger.getId());
            hashMap.put("formModel", formModel);
            String url = req.getHeader("referer");
            hashMap.put("url", url);
            loadFromToItems(hashMap, formModel.getPassengerRouteId());
            return new ModelAndView("ticket.edit", hashMap);
        } else {
            IPassenger passenger = savePassengerIfNew(formModel);
            for (int i = 0; i < quantity; i++) {
                final ITicket entity = fromDtoConverter.apply(formModel);
                entity.setPassenger(passenger);
                ticketService.save(entity);
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.toString().equals("ROLE_USER")) {
                    return "redirect:/personalPage";
                }
            }
            return "redirect:/ticket";
        }
    }

    private IPassenger savePassengerIfNew(TicketDTO ticketDTO) {
        IPassenger passenger;
        Integer passengerId = ticketDTO.getPassengerId();
        if (passengerId == null) {
            passenger = passengerFromDTOConverter.apply(ticketDTO.getPassenger());
            IUserAccount account = userAccountService.createEntity();
            account.setId(AuthHelper.getLoggedUserId());
            passenger.setUserAccount(account);
            passengerService.save(passenger);
        } else {
            passenger = passengerService.createEntity();
            passenger.setId(passengerId);
        }

        return passenger;

    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id", required = true) final Integer id) {
        ticketService.delete(id);
        return "redirect:/ticket";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
        final ITicket dbModel = ticketService.getFullInfo(id);
        final TicketDTO dto = toDtoConverter.apply(dbModel);
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);
        hashMap.put("readonly", true);

        return new ModelAndView("dbTicket.edit", hashMap);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
        final TicketDTO dto = toDtoConverter.apply(ticketService.getFullInfo(id));

        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("formModel", dto);

        loadCommonFormModels(hashMap);

        return new ModelAndView("dbTicket.edit", hashMap);
    }

    private void loadFromToItems(Map<String, Object> hashMap, Integer routeId) {
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

    private void loadCommonFormModels(final Map<String, Object> hashMap) {
        final List<IPassengerRoute> routes = routeService.getAll();

        final Map<Integer, Integer> models = routes.stream()
                .collect(Collectors.toMap(IPassengerRoute::getId, IPassengerRoute::getId));
        hashMap.put("passengerRoutesChoices", models);

    }

}
