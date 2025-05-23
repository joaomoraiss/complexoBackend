package com.example.Complexo.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.Complexo.model.*;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
    List<Artist> getArtistByartistName(String name);
}
