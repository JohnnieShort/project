package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;

public interface ITicketService {

	ITicket get(Integer id);

	List<ITicket> getAll();

	void save(ITicket entity);

	void delete(Integer id);

	void deleteAll();

	ITicket createEntity();

	List<ITicket> find(TicketFilter filter);
}