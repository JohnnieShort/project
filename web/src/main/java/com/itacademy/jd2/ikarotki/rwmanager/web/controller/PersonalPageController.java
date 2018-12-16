package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IRouteItemService;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.UserAccountDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/personalPage")
public class PersonalPageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalPageController.class);
    private IPassengerService passengerService;
    private PassengerToDTOConverter passengerToDTOConverter;
    private IUserAccountService userAccountService;
    private UserAccountToDTOConverter userToDTOConverter;
    private ITicketService ticketService;
    private IRouteItemService itemService;

    @Autowired
    public PersonalPageController(IPassengerService passengerService, PassengerToDTOConverter passengerToDTOConverter,
            IUserAccountService userAccountService, UserAccountToDTOConverter userToDTOConverter,
            ITicketService ticketService, IRouteItemService itemService) {
        this.passengerService = passengerService;
        this.passengerToDTOConverter = passengerToDTOConverter;
        this.userAccountService = userAccountService;
        this.userToDTOConverter = userToDTOConverter;
        this.ticketService = ticketService;
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showPage() {
        Map<String, Object> hashMap = new HashMap<String, Object>();

        IUserAccount userAccount = userAccountService.getFullInfo(AuthHelper.getLoggedUserId());
        UserAccountDTO userDTO = userToDTOConverter.apply(userAccount);
        hashMap.put("user", userDTO);

        Integer loggedUserId = AuthHelper.getLoggedUserId();
        IPassenger passenger = passengerService.get(loggedUserId);

        if (passenger != null) {
            hashMap.put("passenger", passengerToDTOConverter.apply(passenger));
            Integer passengerId = passenger.getId();
            getTime(hashMap, passengerId);
        }

        hashMap.put("readonly", true);

        return new ModelAndView("personalPage", hashMap);
    }

    private void getTime(Map<String, Object> hashMap, Integer passengerId) {
        TicketFilter ticketFilter = new TicketFilter();
        ticketFilter.setFetchPassenger(true);
        ticketFilter.setFetchPassengerRoute(true);
        ticketFilter.setFetchStationFrom(true);
        ticketFilter.setFetchStationTo(true);
        List<ITicket> tickets = ticketService.findByPassengerId(ticketFilter, passengerId);

        Map<Integer, String> ticketsDTO = tickets.stream()
                .collect(Collectors.toMap(ITicket::getId, ticket -> ticket.getStationFrom().getName() + "->"
                        + ticket.getStationTo().getName() + " price: " + ticket.getPrice()));
        hashMap.put("tickets", ticketsDTO);
        Map<Integer, String> departure = itemService.getDeparture(tickets);
        hashMap.put("departure", departure);
        Map<Integer, String> arrival = itemService.getArrival(tickets);
        hashMap.put("arrival", arrival);
    }

}
