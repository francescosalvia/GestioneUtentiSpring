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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    public String estraiToken(String token) {

        String[] tokenArray = token.split(" ");

        return  tokenArray[1];
    }


    @PostMapping("/registrazione")
    public ResponseEntity<GenericResponse> registraUtente(@Valid @RequestBody RequestUtente newUtente) {

        if (controlloEmail(newUtente.getEmail())) {

            if (controlloPassword(newUtente.getPassword())) {
                serviceUtente.registraUtente(newUtente);
                return ResponseEntity.ok(new GenericResponse("Utente registrato", null));
            } else {
                logger.info("password non valida riprovare");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("password non valida riprovare", null));
            }


        } else {
            logger.info("Email non valida riprovare");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("Email non valida riprovare", null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> loginUtente(@Valid @RequestBody RequestLogin newLogin) {
        if (controlloEmail(newLogin.getEmail())) {

            if (controlloPassword(newLogin.getPassword())) {
                logger.info("Utente loggato");
                return ResponseEntity.ok(new GenericResponse("Ok", serviceUtente.loginUtente(newLogin)));

            } else {

                logger.info("password non valida riprovare");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("password non valida riprovare", null));
            }

        } else {
            logger.info("Email non valida riprovare");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("Email non valida riprovare", null));
        }

    }

    @PostMapping("/informazioni")
    public ResponseEntity<GenericResponse> informazioniUtente(@RequestHeader(value="Authorization") String token) {

        Optional<Utente> utente = serviceUtente.informazioniUtente(estraiToken(token));
        if (utente.isPresent()) {
            logger.info("Utente trovato");
            return ResponseEntity.ok(new GenericResponse("Utente trovato", utente.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("Utente non trovato", null));
        }
    }

    @PostMapping("/modifica")
    public ResponseEntity<GenericResponse> modificaUtente(@Valid @RequestBody RequestModificaUtente modUtente,@RequestHeader(value="Authorization") String token) {

        Optional<Utente> utente = serviceUtente.modificaUtente(modUtente,estraiToken(token));
        if (utente.isPresent()) {
            return ResponseEntity.ok(new GenericResponse("Utente trovato", utente.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("Modifica non eseguita", null));
        }


    }

    @PostMapping("/modificapassword")
    public ResponseEntity<GenericResponse> modificaUtente(@RequestParam(value = "password") String password,@RequestHeader(value="Authorization") String token) {

        if (controlloPassword(password)) {
            Optional<Utente> utente = serviceUtente.modificaPassword(password, estraiToken(token));
            if (utente.isPresent()) {
                return ResponseEntity.ok(new GenericResponse("password verificata", utente.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("Modifica password non eseguita", null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new GenericResponse("password non valida", null));
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<GenericResponse> modificaUtente(@RequestHeader(value="Authorization") String token) {

        return ResponseEntity.ok(new GenericResponse(serviceUtente.logoutUtente(estraiToken(token)), null));
    }


}
