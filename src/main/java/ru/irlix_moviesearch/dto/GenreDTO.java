package ru.irlix_moviesearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность жанра")
public class GenreDTO {
    private String name;
    private String description;
}
