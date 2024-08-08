package ru.irlix_moviesearch.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.irlix_moviesearch.dto.CriticDTO;
import ru.irlix_moviesearch.model.Critic;
import ru.irlix_moviesearch.service.CriticService;

import java.util.List;

@RestController
@RequestMapping("/api/critics")
@RequiredArgsConstructor
@Tag(name="Критик", description="Данный контроллер содержит CRUD операции с сущностью критик")
public class CriticController {

    private final CriticService criticService;

    @GetMapping
    public List<CriticDTO> getAll() {
        return criticService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriticDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(criticService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<CriticDTO> create(@RequestBody Critic critic) {
        CriticDTO criticDTO = criticService.create(critic);
        return ResponseEntity.ok(criticDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Critic> update(@PathVariable Long id,
                                         @RequestBody Critic critic) {
        Critic updatedCritic = criticService.update(id, critic);
        return ResponseEntity.ok(updatedCritic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        criticService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
