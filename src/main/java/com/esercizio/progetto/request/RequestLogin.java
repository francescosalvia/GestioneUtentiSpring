package com.esercizio.progetto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestLogin {

    @NotNull(message = "Campo obbligatorio, Inserire un nome")
    private String email;

    @Size(min = 6)
    @NotNull(message = "Campo obbligatorio, Inserire una password")
    private String password;


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
}

