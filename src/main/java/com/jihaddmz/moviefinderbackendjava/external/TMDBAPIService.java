package com.jihaddmz.moviefinderbackendjava.external;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class TMDBAPIService {

    private final WebClient webClient;

    private final String tmdbApiKey;

    public TMDBAPIService() {
        Dotenv dotenv = Dotenv.load();
        this.tmdbApiKey = dotenv.get("TMDB_API");
        this.webClient = WebClient.create();
    }

    public Mono<MovieApiResponse> fetchMovies(int page) {
        return webClient.get().uri("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&page=" + page).header("Authorization", "Bearer " + tmdbApiKey).header("Accept", "application/json").retrieve().bodyToMono(MovieApiResponse.class);
    }
}
