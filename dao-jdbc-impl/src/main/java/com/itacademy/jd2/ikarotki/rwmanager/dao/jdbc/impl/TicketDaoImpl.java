package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.model.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Ticket;

public class TicketDaoImpl extends AbstractDaoImpl<ITicket, Integer> implements ITicketDao {

    @Override
    public ITicket createEntity() {
        return new Ticket();
    }

   

    @Override
    protected String getTableName() {
        return "ticket";
    }



	@Override
	public void update(ITicket entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void insert(ITicket entity) {
		// TODO Auto-generated method stub
		
	}



}
