package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;

public class Train extends BaseEntity implements ITrain{
private Integer locomotiveId;

	public Integer getLocomotiveId() {
		return locomotiveId;
	}

	public void setLocomotiveId(Integer locomotiveId) {
		this.locomotiveId = locomotiveId;
	}

}
