package com.example.Complexo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import com.example.Complexo.model.Artist;
import com.example.Complexo.model.User;
import com.example.Complexo.service.ArtistService;
import com.example.Complexo.service.UserService;

@RestController
@RequestMapping("/artistas")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist, @RequestParam Long studioId) {
        User user = userService.getUserById(studioId);

        // Associa o estúdio ao artista
        artist.setArtistStudio(user);

        // Salva o artista com a associação
        Artist newArtist = artistService.createArtist(artist);

        // (Opcional) Atualiza a lista de artistas do estúdio
        userService.addArtistToStudio(user, newArtist);
        
        return ResponseEntity.status(201).body(newArtist);
    }

    @GetMapping("/studio/{studioId}")
    public ResponseEntity<?> getArtistByStudioId (@PathVariable Long studioId) {
        try {
            List<Artist> artistas = artistService.getAllArtists();
            List<Artist> artistasAchados = new ArrayList<>();
            for (Artist artist : artistas) {
                if (artist.getArtistStudio() == null) {
                    continue; // Skip artists without a studio
                }
                if (artist.getArtistStudio().getStudioId().equals(studioId)) {
                    artistasAchados.add(artist);
                }
            }
            return ResponseEntity.ok(artistasAchados);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
        Optional<Artist> artist = artistService.getArtistById(artistId);
        return artist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByName")
    public ResponseEntity<?> getArtistByName(@RequestParam String name){
        try {
            List<Artist> artistas = artistService.getAllArtists();
            List<Artist> artistasAchados = new ArrayList<>();
            for(Artist artist : artistas){
                if (artist.getArtistName().trim().equalsIgnoreCase(name.trim())) {
                    artistasAchados.add(artist);
                }
            }
            return ResponseEntity.ok(artistasAchados);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
