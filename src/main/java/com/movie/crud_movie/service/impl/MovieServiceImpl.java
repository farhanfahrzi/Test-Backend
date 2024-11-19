package com.movie.crud_movie.service.impl;

import com.movie.crud_movie.model.Movie;
import com.movie.crud_movie.repository.MovieRepository;
import com.movie.crud_movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getById(String id) {
        return movieRepository.findById(id);
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteById(String id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> searchMovies(String title) {
        return movieRepository.findAllByTitleLike(title);
    }
}
