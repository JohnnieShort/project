package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;

public interface IWagonService {
	IWagon get(Integer id);

    List<IWagon> getAll();

    void save(IWagon entity);

    void delete(Integer id);

    void deleteAll();

    IWagon createEntity();
}
