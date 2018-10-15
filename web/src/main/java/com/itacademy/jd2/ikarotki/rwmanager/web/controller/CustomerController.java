package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.service.ICustomerService;
import com.itacademy.jd2.ikarotki.rwmanager.web.converter.CustomerToDTOConverter;
import com.itacademy.jd2.ikarotki.rwmanager.web.dto.CustomerDTO;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
	private ICustomerService customerService;
	CustomerToDTOConverter toDtoConverter;

	@Autowired
	private CustomerController(ICustomerService customerService, CustomerToDTOConverter toDtoConverter) {
		super();
		this.customerService = customerService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final List<ICustomer> entities = customerService.getAll();
		List<CustomerDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final HashMap<String, Object> models = new HashMap<>();
		models.put("list", dtos);
		return new ModelAndView("customer.list", models);
	}
}
