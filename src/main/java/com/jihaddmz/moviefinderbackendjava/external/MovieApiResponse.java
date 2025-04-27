package com.jihaddmz.moviefinderbackendjava.external;

import com.jihaddmz.moviefinderbackendjava.entities.EntityMovie;
import lombok.Data;

import java.util.List;

@Data
public class MovieApiResponse {
    private int page;
    private List<EntityMovie> results;
    private int totalPages;
    private int totalResults;

    // Getters and Setters
}
