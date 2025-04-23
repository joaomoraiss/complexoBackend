package com.example.Complexo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Complexo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBystudioEmail(String studioEmail);
}
