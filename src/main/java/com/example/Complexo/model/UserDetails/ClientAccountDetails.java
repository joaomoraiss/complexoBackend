package com.example.Complexo.model.UserDetails;

import com.example.Complexo.model.Cliente;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

// para Cliente
public class ClientAccountDetails implements AccountDetails {
    private final Cliente cliente;

    public ClientAccountDetails(Cliente c) {
        this.cliente = c;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + cliente.getRole());
    }
    @Override public String getPassword() { return cliente.getPassword(); }
    @Override public String getUsername() { return cliente.getEmail(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
    @Override public Long getId() { return cliente.getId(); }
    @Override public String getRole() { return cliente.getRole().toString(); }
}

