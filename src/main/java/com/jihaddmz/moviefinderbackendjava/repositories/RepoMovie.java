package com.jihaddmz.moviefinderbackendjava.repositories;

import com.jihaddmz.moviefinderbackendjava.entities.EntityMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepoMovie extends JpaRepository<EntityMovie, Integer> {

    List<EntityMovie> findAllByTitleContainsIgnoreCase(String title);
}
