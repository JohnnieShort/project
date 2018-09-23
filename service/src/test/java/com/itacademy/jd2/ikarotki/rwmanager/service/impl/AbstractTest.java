package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Random;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;


public abstract class AbstractTest {
    protected ITicketService ticketService = new TicketServiceImpl();

    private static final Random RANDOM = new Random();

    protected String getRandomPrefix() {
        return RANDOM.nextInt(99999) + "";
    }

    protected int getRandomObjectsCount() {
        return RANDOM.nextInt(9) + 1;
    }

    public Random getRANDOM() {
        return RANDOM;
    }

    protected ITicket saveNewBrand() {
        final ITicket entity = ticketService.createEntity();
     //   entity.setName("brand-" + getRandomPrefix());
        ticketService.save(entity);
        return entity;
    }

}
