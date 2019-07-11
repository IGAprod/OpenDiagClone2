package com.example.opendiagclone.models;

public class Information {
    private String parametres;
    private String value;

    public String getParametrs() {
        return parametres;
    }

    public void setParametres(String parametrs) {
        this.parametres = parametrs;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Information(String parametres, String value) {
        this.parametres = parametres;
        this.value = value;
    }

    public Information() {
    }
}
