package com.itacademy.jd2.ikarotki.rwmanager.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;

public class TicketServiceTest extends AbstractTest {

    @Test
    public void testCreate() {
        final ITicket entity = saveNewBrand();

        final ITicket entityFromDb = ticketService.get(entity.getId());

       // assertEquals(entity.getName(), entityFromDb.getName());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }
}
