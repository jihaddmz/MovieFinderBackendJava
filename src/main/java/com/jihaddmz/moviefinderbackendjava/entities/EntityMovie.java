package com.jihaddmz.moviefinderbackendjava.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class EntityMovie {

    @Id
    private Long id;

    private String poster_path;
    private boolean adult;

    @Column(length = 1000)
    private String overview;

    private String release_date;

    @ElementCollection
    private List<Integer> genre_ids;

    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;

    // Constructors
    public EntityMovie() {}

    // Getters and Setters
    // (You can generate these using your IDE or with Lombok)
}
