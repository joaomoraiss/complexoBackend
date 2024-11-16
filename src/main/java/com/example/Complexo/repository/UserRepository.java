package com.example.Complexo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Complexo.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
