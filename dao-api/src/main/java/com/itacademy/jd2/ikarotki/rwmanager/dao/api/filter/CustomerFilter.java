package com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter;

public class CustomerFilter extends AbstractFilter{
	private boolean fetchUserAccount;

	public boolean isFetchUserAccount() {
		return fetchUserAccount;
	}

	public void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}
	
}
