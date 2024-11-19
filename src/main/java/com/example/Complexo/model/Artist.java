package com.example.Complexo.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString @NoArgsConstructor @Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    private String artistName;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private User artistStudio;

    private String artistStyle;
    private String artistDescription;
    private String artistBiography;
    private String instagramLink;

    @OneToMany(mappedBy = "artistWork")
    private List<Work> artistWorks;
}
