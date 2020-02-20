package com.esercizio.progetto.dao;

import com.esercizio.progetto.data.Utente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

@Component
public class DaoUtente extends DaoGeneral {

    private static final Logger logger = LoggerFactory.getLogger(DaoUtente.class);

    @Transactional
    public Optional<Utente> findUtente(Integer idUtente) {

        Query query = getEm().createNativeQuery("SELECT * from utente where id_utente =  ?", Utente.class);
        query.setParameter(1, idUtente);
        Utente utente1 = (Utente) query.getSingleResult();

        return Optional.of(utente1);

    }

    @Transactional
    public Optional<Utente> findUtenteByEmail(String email) {

        Query query = getEm().createNativeQuery("SELECT * from utente where email =  ?", Utente.class);
        query.setParameter(1, email);

        try {
            Utente utente1 = (Utente) query.getSingleResult();
            return Optional.of(utente1);
        } catch (NoResultException e) {
            Optional<Utente> utente = Optional.empty();
            logger.error("NoResultException nel metodo findUtenteByEmail, nessun utente trovato con questa email {}", email);
            return utente;
        }

    }


}
