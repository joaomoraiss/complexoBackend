package com.example.Complexo.model;


import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString @NoArgsConstructor @Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studioId;

    private String studioName;
    private String studioAdress;
    private String studioDescription;
    private String studioEmail;
    private String studioPassword;

    @OneToMany(mappedBy="artistStudio")
    private List<Artist> studioArtists;

    private List<String> studioImages;
    private String studioLocation;
    private String studioInstagram;
}
