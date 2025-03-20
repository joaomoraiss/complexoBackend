package com.example.Complexo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data @Entity
@Table(name = "app_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    private int stars;

    private String emailAdress;

    private String personName;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    private User studioReview; //estudio avaliado

}
