package com.esercizio.progetto.repository;

import com.esercizio.progetto.data.Login;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends CrudRepository<Login, Integer> {

    List<Login> findAll();

    Optional<Login> findLoginByToken(String token);

}
