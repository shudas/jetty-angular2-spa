package com.shudas.example.jettyangular2spa.auth.models;

/**
* Simple POJO containing the login parameters
*/
public class LoginParams {
    private String email;
    private String password;

    // Need empty constructor for Jackson
    public LoginParams() {}

    public LoginParams(String email, String password) {
        this.email = email;
        this.password = password;
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
}