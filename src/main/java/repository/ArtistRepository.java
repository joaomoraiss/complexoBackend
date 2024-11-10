package repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
}
