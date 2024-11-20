package com.movie.crud_movie.repository;

import com.movie.crud_movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(value = "SELECT * FROM m_movies WHERE title ILIKE %:title%", nativeQuery = true)
    List<Movie> findAllByTitleLikeNative(@Param("title") String title);

    @Query(value = "SELECT * FROM m_movies WHERE director = :director", nativeQuery = true)
    List<Movie> findByDirectorNative(@Param("director") String director);
}

