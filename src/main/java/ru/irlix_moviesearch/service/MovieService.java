package ru.irlix_moviesearch.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.irlix_moviesearch.dto.MovieDTO;
import ru.irlix_moviesearch.model.Movie;
import ru.irlix_moviesearch.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    public final MovieRepository movieRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Page<MovieDTO> getAll(PageRequest pageRequest) {

        Page<Movie> moviePage = movieRepository.findAll(pageRequest);
        Page<MovieDTO> movieDTOPage = moviePage.map(movie -> modelMapper.map(movie, MovieDTO.class));
        return movieDTOPage;
    }

    public MovieDTO getById(Long id) {
        return convertToDTO(movieRepository.findById(id).orElse(null));
    }

    public MovieDTO findByName(String name) {
        return convertToDTO(movieRepository.findByName(name));
    }

    public List<MovieDTO> findByYear(String yearShow) {
        List<Movie> movies = movieRepository.findByYearShow(yearShow);

        return movies
                .stream()
                .map(movie -> convertToDTO(movie))
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findByRating(Double rating) {
        List<Movie> movies = movieRepository.findByRating(rating);

        return movies
                .stream()
                .map(movie -> convertToDTO(movie))
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findByYearBetween(String startYear, String endYear) {
        List<Movie> movies = movieRepository.findByYearBetween(startYear,endYear);

        return movies.stream()
                .map(movie -> convertToDTO(movie))
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findByRatingBetween(String startRating, String endRating) {
        List<Movie> movies = movieRepository.findByRatingBetween(startRating,endRating);

        return movies.stream()
                .map(movie -> convertToDTO(movie))
                .collect(Collectors.toList());
    }

    public List<Movie> findByGenre(Long genreId) {
        return movieRepository.findByGenres_id(genreId);
    }

    public MovieDTO save(Movie movie) {
        Movie movie1 = movieRepository.save(movie);
        return convertToDTO(movie1);
    }

    public Movie update(Long id, Movie movie) {
        Movie updateMovie = movieRepository.findById(id).orElse(null);
        updateMovie.setDescription(movie.getDescription());
        updateMovie.setDuration(movie.getDuration());
        updateMovie.setRating(movie.getRating());
        updateMovie.setYearShow(movie.getYearShow());
        updateMovie.setReviews(movie.getReviews());
        return movieRepository.save(updateMovie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public MovieDTO convertToDTO(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
}
