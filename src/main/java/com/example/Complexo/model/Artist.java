package com.example.Complexo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "app_artists")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;

    @Column(name = "artist_name")
    private String artistName;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    @JsonIgnore
    private User artistStudio;

    @Column(name = "artist_style")
    private String artistStyle;

    @Column(name = "artist_description")
    private String artistDescription;

    @Column(name = "artist_biography")
    private String artistBiography;

    @Column(name = "artist_instagram")
    private String instagramLink;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Agendamento> agendamentos;

    @OneToMany(mappedBy = "artistWork")
    @JsonIgnore
    private List<Work> artistWorks;
}
