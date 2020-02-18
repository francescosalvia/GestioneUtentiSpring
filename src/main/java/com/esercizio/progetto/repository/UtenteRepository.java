package com.esercizio.progetto.repository;


import com.esercizio.progetto.data.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Integer> {

    List<Utente> findAll();

    Optional<Utente> findUtenteByEmail(String email);

    Optional<Utente> findUtenteByIdUtente(Integer idUtente);
}