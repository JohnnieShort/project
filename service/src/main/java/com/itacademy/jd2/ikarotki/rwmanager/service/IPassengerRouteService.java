package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;

public interface IPassengerRouteService {
	IPassengerRoute get(Integer id);

    List<IPassengerRoute> getAll();

    void save(IPassengerRoute entity);

    void delete(Integer id);

    void deleteAll();

    IPassengerRoute createEntity();
}