package ru.irlix_moviesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.irlix_moviesearch.model.Critic;
import ru.irlix_moviesearch.model.Movie;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private double rating;
    private String message;
    private LocalDate date;
    private Movie movie;
    private Critic critic;
}
