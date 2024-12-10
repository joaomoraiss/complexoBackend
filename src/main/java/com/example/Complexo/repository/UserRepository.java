package com.example.Complexo.repository;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Complexo.model.*;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    UserDetails findBystudioEmail(String email);
}
