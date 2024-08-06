package ru.irlix_moviesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriticDTO {

    private String first_name;
    private String last_name;
    private String info;
}
