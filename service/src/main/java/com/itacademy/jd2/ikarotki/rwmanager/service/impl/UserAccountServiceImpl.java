package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IUserAccountDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.UserAccountDaoImpl;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;

public class UserAccountServiceImpl implements IUserAccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
	private IUserAccountDao dao = new UserAccountDaoImpl();

	@Override
	public IUserAccount createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IUserAccount entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new user account created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.info("user account updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IUserAccount get(final Integer id) {
		final IUserAccount entity = dao.get(id);
		LOGGER.info("requested user account: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
		LOGGER.info("user account ID: {} was deleted", id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		LOGGER.warn("All users accounts were deleted");
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = dao.selectAll();
		LOGGER.debug("requested list of users accounts {}", all);
		return all;
	}

}
