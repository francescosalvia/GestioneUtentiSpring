package com.esercizio.progetto.service;

import com.esercizio.progetto.data.Evento;
import com.esercizio.progetto.data.Login;
import com.esercizio.progetto.data.Utente;
import com.esercizio.progetto.repository.EventoRepository;
import com.esercizio.progetto.repository.LoginRepository;
import com.esercizio.progetto.repository.UtenteRepository;
import com.esercizio.progetto.request.RequestLogin;
import com.esercizio.progetto.request.RequestModificaUtente;
import com.esercizio.progetto.request.RequestUtente;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
public class ServiceUtente {

    private static final Logger logger = LoggerFactory.getLogger(ServiceUtente.class);


    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private LoginRepository loginRepository;


    /**
     * METODI UTILI
     **/

    public String generateToken() {
        int length = 8;
        boolean lettere = true;
        boolean numeri = true;

        return RandomStringUtils.random(length, lettere, numeri);
    }


    /**
     * 1) Registrazione utente
     **/


    public void registraUtente(RequestUtente u) {

        Utente utente = new Utente();
        utente.setNome(u.getNome());
        utente.setCognome(u.getCognome());

        utente.setDataNascita(u.getDataNascita());
        utente.setEmail(u.getEmail());
        utente.setPassword(u.getPassword());
        utente.setSesso(u.getSesso());

        utenteRepository.save(utente);

    }


    public String loginUtente(RequestLogin l) {

        Optional<Utente> utente = utenteRepository.findUtenteByEmail(l.getEmail());

        String token = "Utente non loggato";

        if (utente.isPresent()) {

            Utente utente1 = utente.get();

            if (utente1.getPassword().equals(l.getPassword())) {
                logger.info("Utente verificato");

                Evento evento = new Evento();
                Login login = new Login();
                evento.setIdUtente(utente1.getIdUtente());
                evento.setTipoEvento("login");
                evento.setToken(generateToken());

                Evento eventoSalvato = eventoRepository.save(evento);

                login.setToken(eventoSalvato.getToken());
                LocalDateTime time = LocalDateTime.now().plusMinutes(30);
                login.setScadenza(time);
                login.setIdUtente(utente1.getIdUtente());
                login.setIdEvento(eventoSalvato.getIdEvento());


                loginRepository.save(login);
                logger.info("Evento login Salvato");

            token = eventoSalvato.getToken();

        } else {
            logger.warn("Passoword errata");
        }

    } else{
        logger.warn("Nessun utente trovato con questa email");
    }

        return token;
}


    public Optional<Utente> informazioniUtente(String token) {

        Optional<Login> login = loginRepository.findLoginByToken(token);

        Optional<Utente> resultUtente = Optional.empty();

        if (login.isPresent()) {
            LocalDateTime now = LocalDateTime.now();

            Login login1 = login.get();

            long diff = ChronoUnit.SECONDS.between(now, login1.getScadenza());

            if (diff > 0) {
                Optional<Utente> utente = utenteRepository.findUtenteByIdUtente(login1.getIdUtente());
                if (utente.isPresent()) {
                    resultUtente = utente;

                    Evento newEvento = new Evento();
                    newEvento.setTipoEvento("Informazioni Utente");
                    newEvento.setIdUtente(utente.get().getIdUtente());
                    newEvento.setToken(login1.getToken());
                    eventoRepository.save(newEvento);
                    logger.info("Evento Informazioni Utente salvato ");
                }
            } else {
                logger.info("Sessione scaduta");
            }

        }
        return resultUtente;
    }


    public Optional<Utente> modificaUtente(RequestModificaUtente m) {

        Optional<Login> login = loginRepository.findLoginByToken(m.getToken());

        Optional<Utente> resultUtente = Optional.empty();

        if (login.isPresent()) {
            LocalDateTime now = LocalDateTime.now();

            Login login1 = login.get();

            long diff = ChronoUnit.SECONDS.between(now, login1.getScadenza());

            if (diff > 0) {
                Optional<Utente> utente = utenteRepository.findUtenteByIdUtente(login1.getIdUtente());
                if (utente.isPresent()) {

                    Utente newUtente = utente.get();

                    if (StringUtils.isNotBlank(m.getNome())) {
                        newUtente.setNome(m.getNome());
                    }
                    if (StringUtils.isNotBlank(m.getCognome())) {
                        newUtente.setCognome(m.getCognome());
                    }
                    if (StringUtils.isNotBlank(m.getSesso())) {
                        newUtente.setSesso(m.getSesso());
                    }
                    if (m.getDataNascita() != null) {
                        newUtente.setDataNascita(m.getDataNascita());
                    }

                    utenteRepository.save(newUtente);
                    logger.info("Utente modificato");

                    resultUtente = Optional.of(newUtente);

                    Evento newEvento = new Evento();
                    newEvento.setTipoEvento("Modifica Utente");
                    newEvento.setIdUtente(utente.get().getIdUtente());
                    newEvento.setToken(login1.getToken());
                    eventoRepository.save(newEvento);
                    logger.info("Evento Modifica Utente salvato ");
                }
            } else {
                logger.info("Sessione scaduta");
            }

        }
        return resultUtente;
    }

    public Optional<Utente> modificaPassword(String password, String token) {

        Optional<Login> login = loginRepository.findLoginByToken(token);

        Optional<com.esercizio.progetto.data.Utente> resultUtente = Optional.empty();

        if (login.isPresent()) {
            LocalDateTime now = LocalDateTime.now();

            Login login1 = login.get();

            long diff = ChronoUnit.SECONDS.between(now, login1.getScadenza());

            if (diff > 0) {
                Optional<com.esercizio.progetto.data.Utente> utente = utenteRepository.findUtenteByIdUtente(login1.getIdUtente());
                if (utente.isPresent()) {

                    Utente newUtente = utente.get();

                    newUtente.setPassword(password);

                    utenteRepository.save(newUtente);
                    logger.info("Password modificata");

                    resultUtente = Optional.of(newUtente);

                    Evento newEvento = new Evento();
                    newEvento.setTipoEvento("Modifica Password");
                    newEvento.setIdUtente(utente.get().getIdUtente());
                    newEvento.setToken(login1.getToken());
                    eventoRepository.save(newEvento);
                    logger.info("Evento Modifica Password salvato ");
                }
            } else {
                logger.info("Sessione scaduta");
            }

        }
        return resultUtente;
    }


    public String logoutUtente(String token) {

        String messaggio = "Logout effettuato";
        Optional<Login> login = loginRepository.findLoginByToken(token);

        if (login.isPresent()) {

            LocalDateTime now = LocalDateTime.now();

            Login login1 = login.get();

            long diff = ChronoUnit.SECONDS.between(now, login1.getScadenza());

            if (diff > 0) {

                login1.setScadenza(now);

                loginRepository.save(login1);

                Evento newEvento = new Evento();
                newEvento.setTipoEvento("Logout");
                newEvento.setIdUtente(login1.getIdUtente());
                newEvento.setToken(login1.getToken());
                eventoRepository.save(newEvento);
                logger.info("Evento Logout salvato ");
            } else {
                logger.info("Sessione scaduta");
                messaggio = "Sessione gia scaduta";
            }
        }

        return messaggio;
    }














}
