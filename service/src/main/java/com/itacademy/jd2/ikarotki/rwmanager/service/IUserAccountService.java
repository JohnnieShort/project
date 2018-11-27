package com.itacademy.jd2.ikarotki.rwmanager.service;

import java.util.List;

import javax.transaction.Transactional;


import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.utils.IMailService;

public interface IUserAccountService {
	IUserAccount get(Integer id);

	boolean checkPassword(String eMail, String password);

	List<IUserAccount> getAll();

	@Transactional
	void save(IUserAccount entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IUserAccount createEntity();

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

	IUserAccount getByEmail(String eMail);

	IUserAccount getFullInfo(Integer id);

	void setMailService(IMailService mailService);
}
