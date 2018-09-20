package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.model.ITicket;



public interface ITicketService {

    ITicket get(Integer id);

    List<ITicket> getAll();

    void save(ITicket entity);

    void delete(Integer id);

    void deleteAll();

    ITicket createEntity();
}