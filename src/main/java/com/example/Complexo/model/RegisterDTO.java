package com.example.Complexo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private UserRole role;

    @NotBlank
    private String nomeEstudio;

    // Construtor vazio para o Jackson
    public RegisterDTO() {
    }

    // Getters & Setters
    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole role() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String nomeEstudio() {
        return nomeEstudio;
    }

    public void setNomeEstudio(String nomeEstudio) {
        this.nomeEstudio = nomeEstudio;
    }

    public String toString() {
        return "RegisterDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", nomeEstudio='" + nomeEstudio + '\'' +
                '}';
    }
}

