package ru.irlix_moviesearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.irlix_moviesearch.dto.GenreDTO;
import ru.irlix_moviesearch.model.Genre;
import ru.irlix_moviesearch.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;


    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getById(id));
    }


    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody Genre genre) {

        return ResponseEntity.ok(genreService.create(genre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id,
                                             @RequestBody Genre genre) {

        return ResponseEntity.ok(genreService.update(id, genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
