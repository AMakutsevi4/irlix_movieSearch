package ru.irlix_moviesearch.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.irlix_moviesearch.dto.MovieDTO;
import ru.irlix_moviesearch.model.Movie;
import ru.irlix_moviesearch.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name="Фильм", description="Данный контроллер содержит CRUD операции с сущностью фильм. " +
        "Также реализована возможность поиска фильма по жанру, вывод информации содержит пагинацию")
public class MovieController {

    private final MovieService movieService;

    @GetMapping()
    public Page<MovieDTO> getAll( @Parameter(description = "Принимает номер страницы")
                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @Parameter(description = "Принимает количество элементов на странице")
                                 @RequestParam(value = "size", defaultValue = "2") Integer size) {
        return movieService.getAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMoviesById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
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
