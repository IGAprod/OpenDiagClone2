package com.example.opendiagclone.models;


public class Parameters {
    private String parameters;
    private String value;

    public Parameters(String parametres, String value) {
        this.parameters = parametres;
        this.value = value;
    }

    public Parameters() {
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
