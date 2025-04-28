package com.example.Complexo.repository;

import com.example.Complexo.model.Cliente;
import com.example.Complexo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByEmail(String studioEmail);
}
