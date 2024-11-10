package repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
