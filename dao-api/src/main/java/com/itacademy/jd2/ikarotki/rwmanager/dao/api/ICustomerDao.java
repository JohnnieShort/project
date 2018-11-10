package com.itacademy.jd2.ikarotki.rwmanager.dao.api;

import java.util.List;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CustomerFilter;

public interface ICustomerDao extends IDao<ICustomer, Integer> {
	//ICustomer getFullInfo(Integer Id);
	
	List<ICustomer> find(CustomerFilter filter);
	
	long getCount(CustomerFilter filter);
}
