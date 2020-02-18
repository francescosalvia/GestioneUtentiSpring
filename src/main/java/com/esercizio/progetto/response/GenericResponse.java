package com.esercizio.progetto.response;

import com.esercizio.progetto.data.Utente;
import org.springframework.http.HttpStatus;

public class GenericResponse {

    private String statusMessage;
    private Object data;
    private HttpStatus httpStatus;

    public GenericResponse(String statusMessage, Object data, HttpStatus httpStatus) {
        this.statusMessage = statusMessage;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
