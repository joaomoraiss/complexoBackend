package com.example.Complexo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Complexo.model.*;
import com.example.Complexo.repository.UserRepository;

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
    public User getUserById(Long studioId) {
        return userRepository.findById(studioId).get();
    }

    public User addArtistToStudio(User user, Artist artist){
        user.getArtistStudio().add(artist);
        return user;
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
        return userRepository.findById(studioId).map(existingUser -> {
            // Atualiza apenas os campos que o frontend pode enviar
            // e que queremos permitir a atualização.
            if (userDetails.getStudioName() != null) {
                existingUser.setStudioName(userDetails.getStudioName());
            }
            if (userDetails.getStudioDescription() != null) {
                existingUser.setStudioDescription(userDetails.getStudioDescription());
            }
            if (userDetails.getStudioInstagram() != null) {
                existingUser.setStudioInstagram(userDetails.getStudioInstagram());
            }
            if (userDetails.getProfilePictureBase64() != null) {
                existingUser.setProfilePictureBase64(userDetails.getProfilePictureBase64());
            }
            // Não atualize email, senha ou outros campos sensíveis aqui,
            // a menos que seja um processo de edição separado e seguro.

            return userRepository.save(existingUser); // Salva o usuário existente com as atualizações
        }).orElseThrow(() -> new RuntimeException("Atualização mal sucedida! Esse estúdio não foi encontrado ou não existe."));
    }
    // --- FIM DA MUDANÇA ---

    @Transactional
    public UserDetails findBystudioEmail(String email) {
        User user = userRepository.findBystudioEmail(email);
    
        if (user == null) {
            throw new UsernameNotFoundException("Usuário com o email '" + email + "' não foi encontrado.");
        }
    
        return user;
    }
    
    @Transactional
    public boolean emailExists(String email) {
        return userRepository.findBystudioEmail(email) != null;
    }
}
