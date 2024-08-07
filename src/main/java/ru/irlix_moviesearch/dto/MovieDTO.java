package ru.irlix_moviesearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.irlix_moviesearch.model.Review;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность фильма")
public class MovieDTO {

    private String name;
    private String description;
    private double duration;
    private double general_assessment;
    private String year_show;
    private Set<Review> reviews = new HashSet<>();
}
