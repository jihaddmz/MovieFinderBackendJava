package com.jihaddmz.moviefinderbackendjava.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(value = EntityLikedMovie.class)
public class EntityLikedMovie {
    @Id
    private int userId;

    @Id
    private int movieId;

    public EntityLikedMovie() {
    }

    public EntityLikedMovie(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
}
