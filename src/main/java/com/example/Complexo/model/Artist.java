package com.example.Complexo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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
@JsonIgnore // manter este JsonIgnore para evitar recursão infinita na serialização pelo amor de deus
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
@JsonIgnore // manter este JsonIgnore
private List<Agendamento> agendamentos;

@OneToMany(mappedBy = "artistWork")
@JsonIgnore // Mantenha este JsonIgnore
private List<Work> artistWorks;


    @ElementCollection
    @CollectionTable(name = "artist_images", joinColumns = @JoinColumn(name = "artist_id"))
    @Column(name = "image_url", columnDefinition = "TEXT") // Nome da coluna na tabela de coleção
    private List<String> artistImages;


}