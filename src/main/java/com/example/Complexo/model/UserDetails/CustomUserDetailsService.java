package com.example.Complexo.model.UserDetails;

import com.example.Complexo.repository.ClienteRepository;
import com.example.Complexo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository studioRepo;
    @Autowired private ClienteRepository clientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // tenta achar estúdio
        var studio = studioRepo.findBystudioEmail(username);
        if (studio != null) {
            return new StudioAccountDetails(studio);
        }
        // se não achou, tenta cliente
        var client = clientRepo.findByEmail(username);
        if (client != null) {
            return new ClientAccountDetails(client);
        }
        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}

