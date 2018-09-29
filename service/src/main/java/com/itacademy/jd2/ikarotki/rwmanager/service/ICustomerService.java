package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;

public interface ICustomerService {
	ICustomer get(Integer id);

    List<ICustomer> getAll();

    void save(ICustomer entity);

    void delete(Integer id);

    void deleteAll();

    ICustomer createEntity();
}
