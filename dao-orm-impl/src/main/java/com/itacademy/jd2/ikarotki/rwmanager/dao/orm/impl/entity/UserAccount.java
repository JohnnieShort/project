package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {
    @Column
    private String eMail;
    @Column(updatable = false)
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @Override
    public String getEMail() {
        return eMail;
    }

    @Override
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserAccount [eMail=" + eMail + ", role=" + role + ", getId()=" + getId() + "]";
    }

}
