package com.itacademy.jd2.ikarotki.rwmanager.service.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;

public class ServiceSpringContextExample {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		System.out.println(context.getBean(ITicketService.class));
	}
}
