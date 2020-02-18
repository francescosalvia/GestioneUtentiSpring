package com.esercizio.progetto.repository;

import com.esercizio.progetto.data.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Integer> {

    List<Evento> findAll();

    Optional<Evento> findEventoByToken(String token);



}
