package com.esercizio.progetto.repository;

import com.esercizio.progetto.data.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Integer> {

    @Transactional(readOnly = true)
    List<Evento> findAll();

    @Transactional(readOnly = true)
    Optional<Evento> findEventoByToken(String token);



}
