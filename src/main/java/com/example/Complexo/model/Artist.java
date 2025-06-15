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
@JsonIgnore // Mantenha este JsonIgnore para evitar recursão infinita na serialização
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
@JsonIgnore // Mantenha este JsonIgnore
private List<Agendamento> agendamentos;

@OneToMany(mappedBy = "artistWork")
@JsonIgnore // Mantenha este JsonIgnore
private List<Work> artistWorks;

    // NOVO CAMPO: Para receber as imagens do front-end
    // Lembre-se que se 'artistWorks' já serve para imagens, você pode ajustar
    // essa lista para aceitar strings Base64 diretamente, ou usar este novo campo.
    // Se 'artistWorks' for apenas para obras (não imagens em Base64), mantenha este novo campo.
    // É importante que o nome deste campo corresponda ao que você está enviando no React (artistImages)
    @ElementCollection // Para armazenar uma coleção de elementos simples (strings, neste caso)
    @CollectionTable(name = "artist_images", joinColumns = @JoinColumn(name = "artist_id"))
    @Column(name = "image_url", columnDefinition = "TEXT") // Nome da coluna na tabela de coleção
    private List<String> artistImages;


    // O método setArtistId que causava erro foi removido.
    // O Lombok (com @Data ou @Setter) já gera o setter correto.
}