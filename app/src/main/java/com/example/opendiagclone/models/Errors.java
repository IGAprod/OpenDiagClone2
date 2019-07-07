package com.example.opendiagclone.models;

public class Errors {

    private String code;
    private String status;
    private String error;

    public Errors(String code, String status, String error) {
        this.code = code;
        this.status = status;
        this.error = error;
    }

    public Errors() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
