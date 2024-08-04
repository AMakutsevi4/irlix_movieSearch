package ru.irlix_moviesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.irlix_moviesearch.model.Review;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private String name;
    private String description;
    private double duration;
    private double general_assessment;
    private String year_show;
    private Set<Review> reviews = new HashSet<>();
}
