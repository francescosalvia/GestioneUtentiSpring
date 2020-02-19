package com.esercizio.progetto.dao;

import com.esercizio.progetto.data.Utente;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class DaoUtente extends DaoGeneral {


    @Transactional
    public Optional<Utente> findUtente(Integer idUtente){

        Query query = getEm().createNativeQuery("SELECT * from utente where id_utente =  ?",Utente.class);
        query.setParameter(1,idUtente);
        Utente utente1 = (Utente) query.getSingleResult();

        return Optional.of(utente1);

    }

    @Transactional
    public Optional<Utente> findUtenteByEmail(String email){

        Query query = getEm().createNativeQuery("SELECT * from utente where email =  ?",Utente.class);
        query.setParameter(1,email);
        Utente utente1 = (Utente) query.getSingleResult();

        return Optional.of(utente1);

    }

    @Transactional
    public boolean check(Utente utente){

        Query query = getEm().createNativeQuery("SELECT * from utente where id_utente =  ?",Utente.class);
        query.setParameter(1,utente.getIdUtente());
        Optional<Utente> utente1 = Optional.of((Utente) query.getSingleResult());

        if (!utente1.isPresent()){
            return true;
        }
        return false;
    }











/*
    List<Utente> findAll();

    Optional<Utente> findUtenteByEmail(String email);

    Optional<Utente> findUtenteByIdUtente(Integer idUtente);*/


}
