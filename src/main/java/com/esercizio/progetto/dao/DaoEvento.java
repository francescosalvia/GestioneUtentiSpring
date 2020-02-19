package com.esercizio.progetto.dao;

import com.esercizio.progetto.data.Evento;
import com.esercizio.progetto.data.Login;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Component
public class DaoEvento extends  DaoGeneral {




    @Transactional
    public boolean check(Evento evento){

        Query query = getEm().createNativeQuery("SELECT * from login where id_login =  ?",Login.class);
        query.setParameter(1, evento.getIdEvento());
        Evento evento1 = (Evento) query.getSingleResult();

        if (evento1 == null) {
            return false;
        }
        return true;
    }
}
