package com.jihaddmz.moviefinderbackendjava.repositories;

import com.jihaddmz.moviefinderbackendjava.entities.EntityLikedMovie;
import com.jihaddmz.moviefinderbackendjava.entities.EntityMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepoLikedMovie extends JpaRepository<EntityLikedMovie, Integer> {

    @Query(value = "select m.* from entity_movie m left join entity_liked_movie lm on m.id=lm.movie_id where lm.user_id=:userId", nativeQuery = true)
    List<EntityMovie> getLikedMoviesByUserId(int userId);

    @Query(value = "select m.* from entity_movie m left join entity_liked_movie lm on m.id=lm.movie_id where lm.user_id=:userId and lm.movie_id=:movieId", nativeQuery = true)
    Optional<EntityMovie> getLikedMovie(int userId, int movieId);

    Optional<EntityLikedMovie> findByMovieIdAndUserId(int movieId, int userId);
}
