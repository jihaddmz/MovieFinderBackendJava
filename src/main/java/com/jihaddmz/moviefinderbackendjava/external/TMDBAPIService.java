package com.jihaddmz.moviefinderbackendjava.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class TMDBAPIService {

    private final WebClient webClient;

    @Value("app.TMDBAPI_KEY")
    private String tmdbApiKey;

    public TMDBAPIService() {
        this.webClient = WebClient.create();
    }

    public Mono<MovieApiResponse> fetchMovies(int page) {
        return webClient.get().uri("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&page=" + page).header("Authorization", "Bearer " + tmdbApiKey).header("Accept", "application/json").retrieve().bodyToMono(MovieApiResponse.class);
    }
}
