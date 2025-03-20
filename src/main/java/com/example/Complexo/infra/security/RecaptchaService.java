package com.example.Complexo.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RecaptchaService {

    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean validateRecaptcha(String recaptchaToken) {
        RestTemplate restTemplate = new RestTemplate();

        String url = GOOGLE_RECAPTCHA_VERIFY_URL + "?secret=" + recaptchaSecret + "&response=" + recaptchaToken;
        Map<String, Object> response = restTemplate.postForObject(url, null, Map.class);

        if (response == null || !Boolean.TRUE.equals(response.get("success"))) {
            return false;
        }

        return true;
    }
}
