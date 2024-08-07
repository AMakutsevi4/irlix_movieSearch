package ru.irlix_moviesearch.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Кинопоиск demo",
                description = "Приложение подборки кино", version = "1.0.0",
                contact = @Contact(
                        name = "Александр Макуцевич",
                        email = "amakutsevich@bk.ru",
                        url = "https://amakutsevich@bk.ru"
                )
        )
)
public class OpenApiConfig {
}
