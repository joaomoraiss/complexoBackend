package com.example.Complexo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Complexo.model.Artist;
import com.example.Complexo.repository.ArtistRepository;
import java.util.List;


import jakarta.transaction.Transactional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> getArtistsByName(String name){
        return artistRepository.findByArtistNameIgnoreCase(name);
    }


    @Transactional
    public Optional<Artist> getArtistById(Long artistId) {
        return artistRepository.findById(artistId);
    }

    @Transactional
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Transactional
    public void deleteArtistById(Long artistId) {
        if (!artistRepository.existsById(artistId)) {
            throw new RuntimeException("Deleção mal sucedida! Esse artista não foi encontrado ou não existe.");
        }
        artistRepository.deleteById(artistId);
    }

    @Transactional
    public Artist updateArtistById(Long artistId, Artist artistDetails) {
        return artistRepository.findById(artistId).map(artist -> {
            artist.setArtistName(artistDetails.getArtistName());
            artist.setArtistStyle(artistDetails.getArtistStyle());
            artist.setArtistDescription(artistDetails.getArtistDescription());
            artist.setArtistBiography(artistDetails.getArtistBiography());
            artist.setInstagramLink(artistDetails.getInstagramLink());
            artist.setArtistStudio(artistDetails.getArtistStudio());
            return artistRepository.save(artist);
        }).orElseThrow(() -> new RuntimeException("Atualização mal sucedida! Esse artista não foi encontrado ou não existe."));
    }

}
