package com.esercizio.progetto.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="utente")
public class Utente {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id_utente")
    private Integer idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private LocalDate dataNascita;
    private String sesso;


    public Utente() {
    }

    public Utente(String nome, String cognome, String email, String password, LocalDate dataNascita, String sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }


    @Override
    public String toString() {
        return "Utente{" +
                "idUtente=" + idUtente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dataNascita=" + dataNascita +
                ", sesso='" + sesso + '\'' +
                '}';
    }
}
