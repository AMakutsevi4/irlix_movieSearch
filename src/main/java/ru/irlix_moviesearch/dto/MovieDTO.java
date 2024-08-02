package ru.irlix_moviesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private String name;
    private String description;
    private double duration;
    private double general_assessment;
    private String year_show;
    private String reviews;
}
