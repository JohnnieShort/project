package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.PassengerFilter;

public interface IPassengerService {
    IPassenger get(Integer id);

    List<IPassenger> getAll();

    @Transactional
    void save(IPassenger entity);

    @Transactional
    void delete(Integer id);

    @Transactional
    void deleteAll();

    IPassenger createEntity();

    List<IPassenger> find(PassengerFilter filter);

    long getCount(PassengerFilter filter);

    IPassenger getFullInfo(Integer id);

}
