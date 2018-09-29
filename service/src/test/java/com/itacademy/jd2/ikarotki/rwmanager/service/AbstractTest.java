package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.service.ITicketService;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.TicketServiceImpl;


public abstract class AbstractTest {
    protected ITicketService ticketService = new TicketServiceImpl();

    private static final Random RANDOM = new Random();
    @BeforeEach
    public void setUpMethod() {
        // clean DB recursive
        //TicketService.deleteAll();
        //TicketService.deleteAll();
    }
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
