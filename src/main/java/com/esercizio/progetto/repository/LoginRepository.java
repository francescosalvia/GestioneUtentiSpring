package com.esercizio.progetto.repository;

import com.esercizio.progetto.data.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends CrudRepository<Login, Integer> {

    @Transactional(readOnly = true)
    List<Login> findAll();

    @Transactional(readOnly = true)
    Optional<Login> findLoginByToken(String token);

}
