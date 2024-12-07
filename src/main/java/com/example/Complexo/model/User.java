package com.example.Complexo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data @Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private Long studioId;

    @Column(name = "studio_name")
    private String studioName;

    @Column(name = "studio_adress")
    private String studioAdress;

    @Column(name = "studio_description")
    private String studioDescription;

    @Column(name = "studio_email")
    private String studioEmail;

    @Column(name = "studio_password")
    private String studioPassword;

    @OneToMany(mappedBy = "artistStudio")
    private List<Artist> artistStudio;

    @Column(name = "studio_images")
    private List<String> studioImages;

    @Column(name = "studio_location")
    private String studioLocation;

    @Column(name = "studio_instagram")
    private String studioInstagram;
}
