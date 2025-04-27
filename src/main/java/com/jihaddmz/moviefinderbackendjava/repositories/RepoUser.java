package com.jihaddmz.moviefinderbackendjava.repositories;

import com.jihaddmz.moviefinderbackendjava.entities.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoUser extends JpaRepository<EntityUser, Integer> {
    Optional<EntityUser> findByEmail(String email);
}
