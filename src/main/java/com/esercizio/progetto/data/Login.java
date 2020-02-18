package com.esercizio.progetto.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="login")
public class Login {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_login")
    private Integer idLogin;
    private Integer idEvento;
    private Integer idUtente;
    private String token;
    private LocalDateTime scadenza;


    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
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



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDateTime scadenza) {
        this.scadenza = scadenza;
    }
}
