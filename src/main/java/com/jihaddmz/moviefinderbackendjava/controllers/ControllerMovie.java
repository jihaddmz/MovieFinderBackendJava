package com.jihaddmz.moviefinderbackendjava.controllers;

import com.jihaddmz.moviefinderbackendjava.entities.EntityMovie;
import com.jihaddmz.moviefinderbackendjava.services.ServiceMovie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class ControllerMovie {

    private final ServiceMovie serviceMovie;

    public ControllerMovie(ServiceMovie serviceMovie) {
        this.serviceMovie = serviceMovie;
    }

    @GetMapping("")
    public Map<String, Object> getMovies(@RequestParam(required = false) String query) {
        Map<String, Object> result = new HashMap<>();
        List<EntityMovie> movies = serviceMovie.getMovies(query);
        result.put("length", movies.size());
        result.put("results", movies);
        return result;
    }

    @GetMapping("/save-movies")
    public void saveMovies() {
        serviceMovie.saveMovies();
    }
}
