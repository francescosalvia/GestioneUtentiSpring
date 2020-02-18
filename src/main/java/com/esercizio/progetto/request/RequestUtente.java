package com.esercizio.progetto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class RequestUtente {

    @NotNull(message = "Campo obbligatorio, Inserire un nome")
    private String nome;

    @NotNull(message = "Campo obbligatorio, Inserire un cognome")
    private String cognome;

    @NotNull(message = "Campo obbligatorio, Inserire una email")
    private String email;

    @Size(min = 6)
    @NotNull(message = "Campo obbligatorio, Inserire una password")
    private String password;


    @NotNull(message = "Campo obbligatorio, Inserire una data di nascita")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascita;


    @NotNull(message = "Campo obbligatorio, Inserire un sesso")
    private String sesso;


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
}
