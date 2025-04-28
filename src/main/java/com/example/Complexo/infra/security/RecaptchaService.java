package com.example.Complexo.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RecaptchaService {

    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean validateRecaptcha(String recaptchaToken) {
        RestTemplate rest = new RestTemplate();
        String url = VERIFY_URL + "?secret=" + recaptchaSecret + "&response=" + recaptchaToken;
        Map<String, Object> resp = rest.postForObject(url, null, Map.class);
        return resp != null && Boolean.TRUE.equals(resp.get("success"));
    }
}
