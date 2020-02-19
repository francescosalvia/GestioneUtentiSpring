package com.esercizio.progetto.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DaoGeneral {


    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void removeAll(String string){
        Query query = em.createNativeQuery("TRUNCATE TABLE " + string);
        query.executeUpdate();

    }




}
