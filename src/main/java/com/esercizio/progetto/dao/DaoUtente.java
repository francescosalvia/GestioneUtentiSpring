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
public class DaoUtente {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Optional<Utente> findUtente(Integer idUtente){

        Query query = em.createNativeQuery("SELECT * from utente where id_utente =  ?",Utente.class);
        query.setParameter(1,idUtente);
        Utente utente1 = (Utente) query.getSingleResult();

        return Optional.of(utente1);

    }













/*
    List<Utente> findAll();

    Optional<Utente> findUtenteByEmail(String email);

    Optional<Utente> findUtenteByIdUtente(Integer idUtente);*/


}
