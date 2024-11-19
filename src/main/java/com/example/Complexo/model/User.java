package com.example.Complexo.model;


import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString @NoArgsConstructor @Data
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studioId;

    private String studioName;
    private String studioAdress;
    private String studioDescription;
    private String studioEmail;
    private String studioPassword;

    @OneToMany(mappedBy = "artistStudio")
    private List<Artist> artistStudio;

    private List<String> studioImages;
    private String studioLocation;
    private String studioInstagram;
}
