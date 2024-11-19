package com.movie.crud_movie.service;

import com.movie.crud_movie.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie create(Movie movie);
    Optional<Movie> getById(String id);
    List<Movie> getAll();
    void deleteById(String id);
    List<Movie> searchMovies(String title);
}
