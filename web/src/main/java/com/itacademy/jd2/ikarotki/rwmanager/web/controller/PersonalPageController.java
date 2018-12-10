package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.service.IPassengerService;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.PassengerToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.UserAccountToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.PassengerDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.UserAccountDTO;
import com.itacademy.jd2.ikarotki.rwmanager.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/personalPage")
public class PersonalPageController {
	private IPassengerService passengerService;
	private PassengerToDTOConverter passengerToDTOConverter;
	private IUserAccountService userAccountService;
	private UserAccountToDTOConverter userToDTOConverter;

	@Autowired
	public PersonalPageController(IPassengerService passengerService, PassengerToDTOConverter passengerToDTOConverter,
			IUserAccountService userAccountService, UserAccountToDTOConverter userToDTOConverter) {
		this.passengerService = passengerService;
		this.passengerToDTOConverter = passengerToDTOConverter;
		this.userAccountService = userAccountService;
		this.userToDTOConverter =userToDTOConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showPage() {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		IUserAccount userAccount = userAccountService.getFullInfo(AuthHelper.getLoggedUserId());
		UserAccountDTO userDTO = userToDTOConverter.apply(userAccount);
		hashMap.put("user", userDTO);
				
		
		Integer loggedUserId = AuthHelper.getLoggedUserId();
		IPassenger passenger = passengerService.getFullInfo(passengerService.getByUAId(loggedUserId));
		PassengerDTO passengerDTO = passengerToDTOConverter.apply(passenger);
		hashMap.put("passenger", passengerDTO);
		hashMap.put("readonly", true);
		return new ModelAndView("personalPage", hashMap);
	}
}
