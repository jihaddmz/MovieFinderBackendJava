package com.jihaddmz.moviefinderbackendjava.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoLikedMovie {
    private int userId;
    private int movieId;
}
