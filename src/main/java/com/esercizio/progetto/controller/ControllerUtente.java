package com.esercizio.progetto.controller;

import com.esercizio.progetto.data.Utente;
import com.esercizio.progetto.request.RequestLogin;
import com.esercizio.progetto.request.RequestModificaUtente;
import com.esercizio.progetto.request.RequestUtente;
import com.esercizio.progetto.response.GenericResponse;
import com.esercizio.progetto.service.ServiceUtente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class ControllerUtente {

    private static final Logger logger = LoggerFactory.getLogger(ControllerUtente.class);

    @Autowired
    private ServiceUtente serviceUtente;



    public boolean controlloEmail(String email) {
        logger.info("Chiamata nel metodo controlloEmail");
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean controlloPassword(String password) {
        logger.info("Chiamata nel metodo controlloPassword");
        boolean upCase = false;
        boolean loCase = false;
        boolean numeri = false;
        if (password.matches(".+[A-Z].+")) {
            upCase = true;
        }
        if (password.matches(".+[a-z].+")) {
            loCase = true;
        }
        if (password.matches(".+[1-9].+")) {
            numeri = true;
        }
        return (upCase && loCase && numeri);
    }




    @PostMapping("/registrazione")
    public GenericResponse registraUtente(@Valid @RequestBody RequestUtente newUtente) {


        String messaggio = "Utente registrato";
        HttpStatus status = HttpStatus.CREATED;
        if (controlloEmail(newUtente.getEmail())) {

            if (controlloPassword(newUtente.getPassword())) {
                serviceUtente.registraUtente(newUtente);
            } else {
                logger.info("password non valida riprovare");
                messaggio = "password non valida riprovare";
                status = HttpStatus.NOT_ACCEPTABLE;
            }


        } else {
            logger.info("Email non valida riprovare");
            messaggio = "Email non valida riprovare";
            status = HttpStatus.NOT_ACCEPTABLE;
        }

        return new GenericResponse(messaggio,null,status);
    }

    @PostMapping("/login")
    public GenericResponse loginUtente(@Valid @RequestBody RequestLogin newLogin) {

        String messaggio;
        HttpStatus status = HttpStatus.ACCEPTED;
        if (controlloEmail(newLogin.getEmail())) {

            if (controlloPassword(newLogin.getPassword())) {
                messaggio = serviceUtente.loginUtente(newLogin);
                logger.info("Utente loggato");
            } else {

                logger.info("password non valida riprovare");
                messaggio = "password non valida riprovare";
                status = HttpStatus.NOT_ACCEPTABLE;
            }

        } else {
            logger.info("Email non valida riprovare");
            messaggio = "Email non valida riprovare";
            status = HttpStatus.NOT_ACCEPTABLE;
        }

        return new GenericResponse(messaggio,null,status);
    }

    @PostMapping("/informazioni")
    public GenericResponse informazioniUtente(@RequestParam(value = "token") String token) {

        String messaggio;
        HttpStatus status = HttpStatus.OK;
        Optional<Utente> utente =  serviceUtente.informazioniUtente(token);
        Utente utente1 = null;
        if (utente.isPresent()){
            messaggio = "Utente trovato";
            logger.info("Utente trovato");
            utente1 = utente.get();
        } else {
            messaggio = "Utente non trovato";
            status = HttpStatus.NOT_ACCEPTABLE;
        }

        return new GenericResponse(messaggio,utente1,status);
    }

    @PostMapping("/modifica")
    public GenericResponse modificaUtente(@Valid @RequestBody RequestModificaUtente modUtente) {

        HttpStatus status = HttpStatus.OK;
        String messaggio = "Sessione scaduta";
        Optional<Utente> utente =  serviceUtente.modificaUtente(modUtente);
        Utente utente1 = null;
        if (utente.isPresent()){
            messaggio = "Utente modificato";
            utente1 = utente.get();
        } else {
            status = HttpStatus.NOT_ACCEPTABLE;
        }

        return new GenericResponse(messaggio,utente1,status);
    }

    @PostMapping("/modificapassword")
    public GenericResponse modificaUtente(@RequestParam(value = "password") String password, @RequestParam(value = "token") String token) {

        HttpStatus status = HttpStatus.OK;
        Utente utente1 = null;
        String messaggio = null;
        if (controlloPassword(password)) {
            Optional<Utente> utente =  serviceUtente.modificaPassword(password,token);
            if (utente.isPresent()){
                messaggio = "password verificata";
                utente1 = utente.get();
            } else {
                status = HttpStatus.NOT_ACCEPTABLE;
            }
        } else {
            messaggio = "password non valida";
        }

        return new GenericResponse(messaggio,utente1,status);
    }

    @PostMapping("/logout")
    public GenericResponse modificaUtente(@RequestParam(value = "token") String token) {

        HttpStatus status = HttpStatus.OK;
        String messaggio = serviceUtente.logoutUtente(token);

        return new GenericResponse(messaggio,null,status);
    }




}
