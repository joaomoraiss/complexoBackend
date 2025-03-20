package com.example.Complexo.model;

public record AuthenticationDTO(String email, String password, String recaptchaToken) {
}
