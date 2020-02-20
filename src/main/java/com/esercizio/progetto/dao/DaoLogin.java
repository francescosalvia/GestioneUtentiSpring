package com.esercizio.progetto.dao;

import com.esercizio.progetto.data.Login;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Optional;

@Component
public class DaoLogin extends  DaoGeneral {



    @Transactional
    public Optional<Login> findLoginByToken(String token){

        Query query = getEm().createNativeQuery("SELECT * from login where token =  ?",Login.class);
        query.setParameter(1,token);
        Login login1 = (Login) query.getSingleResult();

        return Optional.of(login1);

    }


}
