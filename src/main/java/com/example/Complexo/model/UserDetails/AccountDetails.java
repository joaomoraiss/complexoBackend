package com.example.Complexo.model.UserDetails;

import org.springframework.security.core.userdetails.UserDetails;

public interface AccountDetails extends UserDetails {
    Long getId();
    String getRole();
}

