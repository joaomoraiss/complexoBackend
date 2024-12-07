package com.example.Complexo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Complexo.model.*;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //verificar se uma pessoa já avaliou um estúdio(user) com base no email e no estúdio (cada pessoa só pode avaliar uma vez por estudio)
    Optional<Review> findByEmailAdressAndStudioReview(String emailAdress, User studioReview);
}
