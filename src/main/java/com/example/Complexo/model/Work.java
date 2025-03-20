package com.example.Complexo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Entity
@Table(name = "app_works")
@Getter
@Setter
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long workId;

    @ManyToOne
    @JsonIgnore
    private Artist artistWork;

    @Column(name = "work_title")
    private String title;

    @Column(name = "work_description")
    private String description;

    private String imageURL;

    Work(){}
}
