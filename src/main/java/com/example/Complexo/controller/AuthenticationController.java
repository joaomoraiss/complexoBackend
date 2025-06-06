package com.example.Complexo.controller;

import com.example.Complexo.infra.security.RecaptchaService;
import com.example.Complexo.infra.security.TokenService;
import com.example.Complexo.model.*;
import com.example.Complexo.model.UserDetails.AccountDetails;
import com.example.Complexo.repository.ClienteRepository;
import com.example.Complexo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RecaptchaService recaptchaService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        // 1) recaptcha
        if (!recaptchaService.validateRecaptcha(data.recaptchaToken())) {
        return ResponseEntity.badRequest().body("Falha na validação do reCAPTCHA!");
        }

        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.email(), data.password())
            );
            var jwt = tokenService.generateToken((AccountDetails) auth.getPrincipal());

            // 2) Verifica se é studio ou cliente pelo e-mail
            Object user;
            var studio = repository.findBystudioEmail(data.email());
            if (studio != null) {
                user = studio;
            } else {
                user = clienteRepository.findByEmail(data.email());
            }

            return ResponseEntity.ok(new LoginResponseFullDTO(jwt, user));

        } catch (BadCredentialsException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("E-mail ou senha incorretos.");
        }
    }

    @PostMapping("/register/studio")
    public ResponseEntity registerStudio(@RequestBody @Valid RegisterDTO data){
        if (this.repository.findBystudioEmail(data.email()) != null) return ResponseEntity
                .badRequest().body("Email já está registrado!");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.role(), data.email(), encryptedPassword, data.nomeEstudio());

        this.repository.save(newUser);
        return ResponseEntity.ok("Estudio registrado com sucesso!");
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/register/cliente")
    public ResponseEntity registerCliente(@RequestBody @Valid RegisterDTO data){
        System.out.println(data);
        if (clienteRepository.findByEmail(data.email()) != null) return ResponseEntity
                .badRequest().body("Email já está registrado!");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Cliente newUser = new Cliente(data.role(), data.email(), encryptedPassword, data.nomeEstudio());
        System.out.println(newUser);
        clienteRepository.save(newUser);
        return ResponseEntity.ok("Cliente registrado com sucesso!");
    }
}
