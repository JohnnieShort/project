package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ILocomotive;

public class Locomotive extends BaseEntity implements ILocomotive{
private String name;
private Double power;

public Double getPower() {
	return power;
}

public void setPower(Double power) {
	this.power = power;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "Locomotive [name=" + name + ", power=" + power + ", getId()=" + getId() + "]";
}



}
