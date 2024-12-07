package com.example.Complexo.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data @Entity
@Table(name = "app_artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;

    @Column(name = "artist_name")
    private String artistName;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private User artistStudio;

    @Column(name = "artist_style")
    private String artistStyle;

    @Column(name = "artist_description")
    private String artistDescription;

    @Column(name = "artist_biography")
    private String artistBiography;

    @Column(name = "artist_instagram")
    private String instagramLink;

    @OneToMany(mappedBy = "artistWork")
    private List<Work> artistWorks;
}
