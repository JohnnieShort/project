package com.itacademy.jd2.ikarotki.rwmanager.dao.orm.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IndexInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexInitializer.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    private void init() throws InterruptedException {

        LOGGER.info("index intialization started.");
        FullTextEntityManager fullTextEntityManager = Search
                .getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        LOGGER.info("index intialization completed.");
    }

}
