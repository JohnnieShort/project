package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TicketFilter;

public interface ITicketDao extends IDao<ITicket, Integer> {
	List<ITicket> find(TicketFilter filter);
	
	long getCount(TicketFilter filter);

}
