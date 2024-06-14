package com.project.pantry.entities;

public class LoginObject {
    private int id;
    private String username;
    private String email;
    private String password;
    private String loggedIn;

    public LoginObject(int id, String username, String email, String password, String loggedIn) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLoggedIn() {
        return loggedIn;
    }
}
