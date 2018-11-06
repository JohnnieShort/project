package com.itacademy.jd2.ikarotki.rwmanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/administratorPage", method = RequestMethod.GET)
	public String dataManagement() {
		return "administratorPage";
	}
	
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String checkSchedule() {
		return "schedule";
	}
}
