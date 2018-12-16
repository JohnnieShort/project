package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;

@Entity
public class Passenger implements IPassenger {
    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = UserAccount.class)
    @PrimaryKeyJoinColumn
    private IUserAccount userAccount;

    private String street;
    private Integer building;
    private Integer apartments;
    private String phone;

    @Column(updatable = false)
    private Date created;
    @Column
    private Date updated;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public Integer getBuilding() {
        return building;
    }

    @Override
    public void setBuilding(Integer building) {
        this.building = building;
    }

    @Override
    public Integer getApartments() {
        return apartments;
    }

    @Override
    public void setApartments(Integer apartments) {
        this.apartments = apartments;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public IUserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public void setUserAccount(IUserAccount userAccountId) {
        this.userAccount = userAccountId;
    }

    @Override
    public String toString() {
        return "Passenger [Id " + getId() + "]";
    }

}
