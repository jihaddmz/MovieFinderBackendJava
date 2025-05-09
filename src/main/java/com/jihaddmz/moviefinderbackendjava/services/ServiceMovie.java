package com.jihaddmz.moviefinderbackendjava.services;

import com.jihaddmz.moviefinderbackendjava.entities.EntityMovie;
import com.jihaddmz.moviefinderbackendjava.external.TMDBAPIService;
import com.jihaddmz.moviefinderbackendjava.repositories.RepoMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMovie {

    private final RepoMovie repoMovie;
    private TMDBAPIService tmdbAPIService;

    public ServiceMovie(RepoMovie repoMovie) {
        this.repoMovie = repoMovie;
    }

    public void saveMovies() {
        if (tmdbAPIService == null) {
            tmdbAPIService = new TMDBAPIService();
        }
        int page = 1;
        while (page < 11) {
            List<EntityMovie> movies = tmdbAPIService.fetchMovies(page).block().getResults();
            repoMovie.saveAll(movies);
            page++;
        }
    }

    public Page<EntityMovie> fetchMovies(Pageable pageable) {

        return repoMovie.findAll(pageable);
    }

    public List<EntityMovie> getMovies(String query) {
        List<EntityMovie> movies;

        if (query != null) {
            movies = repoMovie.findAllByTitleContainsIgnoreCase(query);
        } else {
            movies = repoMovie.findAll();
        }
        return movies;
    }

}
