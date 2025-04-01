package com.example.Complexo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Complexo.model.Review;
import com.example.Complexo.model.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //verificar se uma pessoa já avaliou um estúdio(user) com base no email e no estúdio (cada pessoa só pode avaliar uma vez por estudio)
    Optional<Review> findByEmailAdressAndStudioReview(String emailAdress, User studioReview);
}