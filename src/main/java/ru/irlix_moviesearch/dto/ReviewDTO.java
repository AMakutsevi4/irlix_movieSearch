package ru.irlix_moviesearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.irlix_moviesearch.model.Critic;
import ru.irlix_moviesearch.model.Movie;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность рецензии")
public class ReviewDTO {

    private double rating;
    private String message;
    private LocalDate date;
    private Movie movie;
    private Critic critic;
}
