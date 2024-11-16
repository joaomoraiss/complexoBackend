package com.example.Complexo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Complexo.service.*;
import com.example.Complexo.model.*;

@RestController
@RequestMapping("/artistas")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist newArtist = artistService.createArtist(artist);
        return ResponseEntity.status(201).body(newArtist);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
        Optional<Artist> artist = artistService.getArtistById(artistId);
        return artist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable Long artistId) {
        artistService.deleteArtistById(artistId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<Artist> updateArtistById(@PathVariable Long artistId, @RequestBody Artist artistDetails) {
        Artist updatedArtist = artistService.updateArtistById(artistId, artistDetails);
        return ResponseEntity.ok(updatedArtist);
    }
}
