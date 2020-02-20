package com.esercizio.progetto.repository;


import com.esercizio.progetto.data.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Integer> {

    @Transactional(readOnly = true)
    List<Utente> findAll();

    @Transactional(readOnly = true)
    Optional<Utente> findUtenteByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Utente> findUtenteByIdUtente(Integer idUtente);
}