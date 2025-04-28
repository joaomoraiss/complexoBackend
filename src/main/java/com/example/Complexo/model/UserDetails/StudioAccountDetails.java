package com.example.Complexo.model.UserDetails;

import com.example.Complexo.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class StudioAccountDetails implements AccountDetails {
    private final User studio;

    public StudioAccountDetails(User studio) {
        this.studio = studio;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + studio.getRole());
    }
    @Override public String getPassword() { return studio.getPassword(); }
    @Override public String getUsername() { return studio.getStudioEmail(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
    @Override public Long getId() { return studio.getStudioId(); }
    @Override public String getRole() { return studio.getRole().toString(); }
}
