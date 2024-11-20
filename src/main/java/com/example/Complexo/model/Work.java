package com.example.Complexo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data @Entity
@Table(name = "app_works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long workId;

    @ManyToOne
    private Artist artistWork;

    @Column(name = "work_title")
    private String title;

    @Column(name = "work_description")
    private String description;

    private String imageURL;
}
