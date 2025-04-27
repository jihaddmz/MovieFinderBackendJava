package com.jihaddmz.moviefinderbackendjava.services;

import com.jihaddmz.moviefinderbackendjava.entities.EntityLikedMovie;
import com.jihaddmz.moviefinderbackendjava.entities.EntityMovie;
import com.jihaddmz.moviefinderbackendjava.repositories.RepoLikedMovie;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceLikedMovie {

    private final RepoLikedMovie repoLikedMovie;

    public ServiceLikedMovie(RepoLikedMovie repoLikedMovie) {
        this.repoLikedMovie = repoLikedMovie;
    }

    private EntityMovie getLikedMovie(int userId, int movieId) {
        Optional<EntityMovie> likedMovie = repoLikedMovie.getLikedMovie(userId, movieId);
        if (likedMovie.isEmpty()) {
            throw new NoSuchElementException("Liked movie not found");
        }

        return likedMovie.get();
    }

    public List<EntityMovie> getLikedMovies(int userId) {
        return repoLikedMovie.getLikedMoviesByUserId(userId);
    }

    public Map<String, Object> saveLikedMovie(int userId, int movieId) {
        repoLikedMovie.save(new EntityLikedMovie(userId, movieId));
        EntityMovie likedMovie = getLikedMovie(userId, movieId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("movie", likedMovie);
        return response;
    }

    public Map<String, Object> deleteLikedMovie(int userId, int movieId) {
        EntityMovie deleteLikedMovie = getLikedMovie(userId, movieId);
        Optional<EntityLikedMovie> entityLikedMovie = repoLikedMovie.findByMovieIdAndUserId(movieId, userId);
        if (entityLikedMovie.isEmpty()) {
            throw new NoSuchElementException("Liked movie not found");
        }
        repoLikedMovie.delete(entityLikedMovie.get());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("movie", deleteLikedMovie);
        return response;
    }
}
