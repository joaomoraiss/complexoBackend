package com.example.Complexo.model;

public enum UserRole {
    STUDIO("studio");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}