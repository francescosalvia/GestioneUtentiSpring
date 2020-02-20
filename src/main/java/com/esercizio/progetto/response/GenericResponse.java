package com.esercizio.progetto.response;

public class GenericResponse {

    private String statusMessage;
    private Object data;


    public GenericResponse(String statusMessage, Object data) {
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public GenericResponse() {

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

    public static GenericResponse ok(String str){
        return new GenericResponse(str,null);
    }

    public static  GenericResponse failed(String str){
        return new GenericResponse(str,null);
    }

    public static GenericResponse okObject(String str,Object object){
        return new GenericResponse(str,object);
    }

}
