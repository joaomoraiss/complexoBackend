package com.example.Complexo.service;
import com.example.Complexo.repository.*;
import com.example.Complexo.model.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //criar usuario/estudio
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //buscar usuario/estudio pelo id
    @Transactional
    public Optional<User> getUserById(Long studioId) {
        return userRepository.findById(studioId);
    }

    //buscar todos os usuarios (não é pra passar o id aqui)
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //deletar um usuario pelo id
    @Transactional
    public void deleteUserById(Long studioId) {
        if (!userRepository.existsById(studioId)) {
            throw new RuntimeException("Deleção mal sucedida! Esse estúdio não foi encontrado ou não existe.");
        }
        userRepository.deleteById(studioId);
    }

    //atualizar usuario pelo id
    @Transactional
    public User updateUserById(Long studioId, User userDetails) {
        return userRepository.findById(studioId).map(user -> {
            userDetails.setStudioId(studioId);
            return userRepository.save(userDetails);
        }).orElseThrow(() -> new RuntimeException("Atualização mal sucedida! Esse estúdio não foi encontrado ou não existe."));
    }
    @Transactional
    public UserDetails findBystudioEmail(String email) {
        UserDetails userDetails = userRepository.findBystudioEmail(email);

        if (userDetails == null) {throw new UsernameNotFoundException("Usuário com o email '"
                + email + "' não foi encontrado.");}

        return (User) userDetails;
    }
}
