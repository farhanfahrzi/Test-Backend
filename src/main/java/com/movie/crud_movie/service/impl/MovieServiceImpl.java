package com.movie.crud_movie.service.impl;

import com.movie.crud_movie.model.Movie;
import com.movie.crud_movie.repository.MovieRepository;
import com.movie.crud_movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Cacheable(value = "movies")
    public List<Movie> getAll() {
        return movieRepository.findAll()
                .stream()
                .sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle())) // Sorting example
                .toList();
    }

    @Cacheable(value = "movie", key = "#id")
    public Optional<Movie> getById(String id) {
        return movieRepository.findById(id);
    }

    @CacheEvict(value = {"movies", "movie"}, allEntries = true)
    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }

    @CacheEvict(value = {"movies", "movie"}, allEntries = true)
    public void deleteById(String id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> searchMovies(String title) {
        return movieRepository.findAllByTitleLikeNative(title)
                .stream()
                .filter(movie -> !movie.getTitle().isBlank())
                .toList();
    }
}

