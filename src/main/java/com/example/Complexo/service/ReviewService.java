package com.example.Complexo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Complexo.model.Review;
import com.example.Complexo.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(Review review) {
        //regra do back porque só uma avaliação por estudio
        Optional<Review> existingReview = reviewRepository.findByEmailAdressAndStudioReview(
            review.getEmailAdress(), review.getStudioReview());
        
        if (existingReview.isPresent()) {
            throw new RuntimeException("Este email já avaliou este estúdio. Apenas é permitido uma avaliação por estúdio.");
        }

        return reviewRepository.save(review);
    }

    // get em todas avaliações
    @Transactional
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // get numa avaliação pelo id
    @Transactional
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // atualizar avaliação
    @Transactional
    public Review updateReview(Long reviewId, Review reviewDetails) {
        return reviewRepository.findById(reviewId).map(existingReview -> {
            existingReview.setComment(reviewDetails.getComment());
            existingReview.setStars(reviewDetails.getStars());
            return reviewRepository.save(existingReview);
        }).orElseThrow(() -> new RuntimeException("Avaliação não encontrada."));
    }

    // excluir uma avaliação
    @Transactional
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new RuntimeException("Avaliação não encontrada.");
        }
        reviewRepository.deleteById(reviewId);
    }
}