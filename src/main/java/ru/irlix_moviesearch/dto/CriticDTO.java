package ru.irlix_moviesearch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность критика")
public class CriticDTO {

    private String first_name;
    private String last_name;
    private String info;
}
