package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IUserAccountDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.UserAccount;

public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	@Override
	public IUserAccount createEntity() {

		return new UserAccount();
	}

	@Override
	public void update(IUserAccount entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(IUserAccount entity) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getTableName() {

		return "user_account";
	}

}
