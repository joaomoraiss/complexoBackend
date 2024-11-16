package com.example.Complexo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.example.Complexo.repository.*;
import com.example.Complexo.model.*;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    //criar artista
    @Transactional
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    //buscar artista pelo id
    @Transactional
    public Optional<Artist> getArtistById(Long artistId) {
        return artistRepository.findById(artistId);
    }

    //buscar todos os artistas (vai retornar uma lista de artistas aqui NÃO é pra passar no id)
    @Transactional
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    //deletar um artista pelo id
    @Transactional
    public void deleteArtistById(Long artistId) {
        if (!artistRepository.existsById(artistId)) {
            throw new RuntimeException("Deleção mal sucedida! Esse artista não foi encontrado ou não existe.");
        }
        artistRepository.deleteById(artistId);
    }

    //atualizar um artista pelo id
    @Transactional
    public Artist updateArtistById(Long artistId, Artist artistDetails) {
        return artistRepository.findById(artistId).map(artist -> {
            artistDetails.setArtistId(artistId);
            return artistRepository.save(artistDetails);
        }).orElseThrow(() -> new RuntimeException("Atualização mal sucedida! Esse artista não foi encontrado ou não existe."));
    }
}
