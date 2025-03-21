package com.example.Complexo.controller;

import com.example.Complexo.infra.security.RecaptchaService;
import com.example.Complexo.infra.security.TokenService;
import com.example.Complexo.model.AuthenticationDTO;
import com.example.Complexo.model.LoginResponseDTO;
import com.example.Complexo.model.RegisterDTO;
import com.example.Complexo.model.User;
import com.example.Complexo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RecaptchaService recaptchaService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        if (!recaptchaService.validateRecaptcha(data.recaptchaToken())) {
            return ResponseEntity.badRequest().body("Falha na validação do reCAPTCHA!");
        }
        var usernamePassword =  new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.repository.findBystudioEmail(data.email()) != null) return ResponseEntity
                .badRequest().body("Email já está registrado!");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.role(), data.email(), encryptedPassword, data.nomeEstudio());

        this.repository.save(newUser);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}
