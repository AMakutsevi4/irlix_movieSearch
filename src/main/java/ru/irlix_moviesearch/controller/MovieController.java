package ru.irlix_moviesearch.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.irlix_moviesearch.dto.MovieDTO;
import ru.irlix_moviesearch.model.Movie;
import ru.irlix_moviesearch.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "Фильм", description = "Данный контроллер содержит CRUD операции с сущностью фильм. " +
        "Также реализована возможность поиска фильма по жанру, вывод информации содержит пагинацию")
public class MovieController {

    private final MovieService movieService;

    @GetMapping()
    public Page<MovieDTO> getAll(@Parameter(description = "Принимает номер страницы")
                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @Parameter(description = "Принимает количество элементов на странице")
                                 @RequestParam(value = "size", defaultValue = "2") Integer size,
                                 @Parameter(description = "Принимает поле, по которому выполняется сортировка (по умолчанию name")
                                 @RequestParam Optional<String> sortBy,
                                 @Parameter(description = "Принимает условия сортировки")
                                 @RequestParam(value = "srt", defaultValue = "desc") String srt) {

        Sort.Direction direction = srt.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest pages = PageRequest.of(page, size, direction, sortBy.orElse("name"));

        return movieService.getAll(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMoviesById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

    @Operation(
            summary = "Поиск фильма по конкретному названию"
    )
    @GetMapping("/searchName")
    public ResponseEntity<MovieDTO> getMoviesByName(@RequestParam String name) {
        return ResponseEntity.ok(movieService.findByName(name));
    }

    @Operation(
            summary = "Поиск фильма по конкретной дате показа"
    )
    @GetMapping("/searchYear")
    public ResponseEntity<List<MovieDTO>> getMoviesByYear(@RequestParam String yearShow) {
        return ResponseEntity.ok(movieService.findByYear(yearShow));
    }

    @Operation(
            summary = "Поиск фильма по конкретному рейтингу"
    )
    @GetMapping("/searchRating")
    public ResponseEntity<List<MovieDTO>> getMoviesByRating(@RequestParam Double rating) {
        return ResponseEntity.ok(movieService.findByRating(rating));
    }

    @Operation(
            summary = "Получение фильмов по жанру",
            description = "Также учитывается, что фильмов может быть несколько"
    )
    @GetMapping("/by-genre/{genre_id}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable Long genre_id) {
        List<Movie> movies = movieService.findByGenre(genre_id);
        return ResponseEntity.ok(movies);
    }

    @Operation(
            summary = "Поиск фильмов в диапазоне",
            description = "Диапазон между дат показа фильма"
    )
    @GetMapping("/searchByYearBetween")
    public ResponseEntity<List<MovieDTO>> getByYearBetween(
            @RequestParam String startYear,
            @RequestParam String endYear) {
        return ResponseEntity.ok(movieService.findByYearBetween(startYear, endYear));
    }

    @Operation(
            summary = "Поиск фильмов в диапазоне",
            description = "Диапазон между рейтинга фильмов"
    )
    @GetMapping("/searchByRatingBetween")
    public ResponseEntity<List<MovieDTO>> getByRatingBetween(
            @RequestParam String startRating,
            @RequestParam String endRating) {
        return ResponseEntity.ok(movieService.findByRatingBetween(startRating, endRating));
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovies(@RequestBody Movie movie) {
        MovieDTO createdMovie = movieService.save(movie);
        return ResponseEntity.ok(createdMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovies(@PathVariable Long id,
                                              @RequestBody Movie movie) {
        Movie updatedMovie = movieService.update(id, movie);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
