package com.esercizio.progetto.dao;

import com.esercizio.progetto.data.Evento;
import com.esercizio.progetto.data.Login;
import com.esercizio.progetto.data.Utente;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Component
public class DaoGeneral {

    DaoUtente daoUtente;
    DaoLogin daoLogin;
    DaoEvento daoEvento;

    @PersistenceContext
    private EntityManager em;


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void removeAll(String string) {
        Query query = em.createNativeQuery("TRUNCATE TABLE " + string);
        query.executeUpdate();

    }

    @Transactional
    public void delete(Object object) {
        em.remove(object);
    }


    @Transactional
    public Object save(Object object) {

        if (object instanceof Utente) {
            if (((Utente) object).getIdUtente() == null) {
                em.persist(object);
            } else {
                em.merge(object);
            }
        }
        if (object instanceof Login) {
            if (((Login) object).getIdLogin() == null) {
                em.persist(object);
            } else {
                em.merge(object);
            }
            return object;
        }
        if (object instanceof Evento) {
            if (((Evento) object).getIdEvento() == null) {
                em.persist(object);
            } else {
                em.merge(object);
            }
        }
        return object;
    }


}
