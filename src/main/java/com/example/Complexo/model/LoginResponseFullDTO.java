package com.example.Complexo.model;

public class LoginResponseFullDTO {
    private String token;
    private Object user;

    public LoginResponseFullDTO(String token, Object user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Object getUser() {
        return user;
    }
}
