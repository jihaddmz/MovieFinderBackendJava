package com.jihaddmz.moviefinderbackendjava.controllers;

import com.jihaddmz.moviefinderbackendjava.dtos.DtoLikedMovie;
import com.jihaddmz.moviefinderbackendjava.entities.EntityMovie;
import com.jihaddmz.moviefinderbackendjava.services.ServiceLikedMovie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/liked-movies")
public class ControllerLikedMovie {

    private final ServiceLikedMovie serviceLikedMovie;

    public ControllerLikedMovie(ServiceLikedMovie serviceLikedMovie) {
        this.serviceLikedMovie = serviceLikedMovie;
    }

    @GetMapping("/{id}")
    public List<EntityMovie> getLikedMovies(@PathVariable("id") int id, HttpServletRequest request) {
        return serviceLikedMovie.getLikedMovies(id);
    }

    @PostMapping("/")
    public Map<String, Object> saveLikedMovie(@RequestBody DtoLikedMovie dto) {
        System.out.println("in controller is " + dto.getUserId());
        return serviceLikedMovie.saveLikedMovie(dto.getUserId(), dto.getMovieId());
    }

    @DeleteMapping("/")
    public Map<String, Object> deleteLikedMovie(@RequestBody Map<String, Integer> body) {
       return serviceLikedMovie.deleteLikedMovie(body.get("userId"), body.get("movieId"));
    }
}
