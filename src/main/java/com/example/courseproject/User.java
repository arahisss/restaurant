package com.example.courseproject;

public class User {
    private String login;
    private String password;
    private long id;
    private long id_role;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return Long.toString(id);
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_role() {
        return id_role;
    }

    public void setId_role(long id_role) {
        this.id_role = id_role;
    }
}
