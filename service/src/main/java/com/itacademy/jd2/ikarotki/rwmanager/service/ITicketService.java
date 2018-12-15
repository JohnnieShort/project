package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;

public interface ITicketService {

	ITicket get(Integer id);

	List<ITicket> getAll();
	@Transactional
	void save(ITicket entity);
	@Transactional
	void delete(Integer id);
	@Transactional
	void deleteAll();

	ITicket createEntity();

	List<ITicket> find(TicketFilter filter);
	
	long getCount(TicketFilter filter);

	ITicket getFullInfo(Integer id);

	List<ITicket> findByPassengerId(TicketFilter ticketFilter, Integer passengerId);
}