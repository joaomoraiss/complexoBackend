package repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{
}
