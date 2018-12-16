package com.itacademy.jd2.ikarotki.rwmanager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IUserAccountDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.ikarotki.rwmanager.service.IUserAccountService;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.utils.IMailService;
import com.itacademy.jd2.ikarotki.rwmanager.service.impl.utils.Password;

@Service
public class UserAccountServiceImpl implements IUserAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);
    private IUserAccountDao dao;
    private IMailService mailService;

    @Autowired
    public UserAccountServiceImpl(IUserAccountDao dao, IMailService mailService) {
        super();
        this.dao = dao;
        this.mailService = mailService;
    }

    @Override
    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public UserAccountServiceImpl() {
        super();
    }

    @Override
    public IUserAccount createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IUserAccount entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getPassword() != null) {
            entity.setPassword(Password.hashPassword(entity.getPassword()));
        }
        if (entity.getRole() == null) {
            entity.setRole(Role.ROLE_USER);
        }
        if (entity.getId() == null) {
            LOGGER.info("new user account created: {}", entity);
            entity.setCreated(modifedOn);
            dao.insert(entity);
            String body = String.format("Congratulations, user %s is registered!!!", entity.getEMail());
            this.mailService.sendEmail(entity.getEMail(), "Registration", body);
        } else {
            LOGGER.info("user account updated: {}", entity);
            dao.update(entity);

        }
    }

    @Override
    public boolean checkPassword(String eMail, String password) {
        boolean checked;
        IUserAccount userAccount = dao.getByEMail(eMail);
        checked = Password.checkPassword(password, userAccount.getPassword());

        return checked;

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

    @Override
    public List<IUserAccount> find(UserAccountFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(UserAccountFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public IUserAccount getByEmail(String eMail) {

        return dao.getByEMail(eMail);
    }

    @Override
    public IUserAccount getFullInfo(Integer id) {
        final IUserAccount entity = dao.getFullInfo(id);
        LOGGER.info("requested user account: {}", entity);
        return entity;
    }
}
