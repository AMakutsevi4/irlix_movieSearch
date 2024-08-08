package ru.irlix_moviesearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.irlix_moviesearch.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByGenres_id(Long genreId);

    Movie findByName(String name);

    List<Movie> findByYearShow(String yearShow);

    List<Movie> findByRating(Double rating);

    @Query("SELECT m FROM Movie m WHERE m.yearShow BETWEEN :startYear AND :endYear")
    List<Movie> findByYearBetween(String startYear, String endYear);

    @Query("SELECT m FROM Movie m WHERE m.rating BETWEEN :startRating AND :endRating")
    List<Movie> findByRatingBetween(String startRating, String endRating);
}
