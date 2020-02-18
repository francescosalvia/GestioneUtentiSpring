package com.esercizio.progetto.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="evento")
public class Evento {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_evento")
    private Integer idEvento;
    private Integer idUtente;
    private String tipoEvento;
    private String token;

    public Evento() {
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", id_utente=" + idUtente +
                ", tipoEvento='" + tipoEvento + '\'' +
                '}';
    }
}
