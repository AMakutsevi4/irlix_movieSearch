package ru.irlix_moviesearch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.irlix_moviesearch.dto.MovieDTO;
import ru.irlix_moviesearch.model.Movie;
import ru.irlix_moviesearch.service.MovieService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {

        var movieDTOs = createMovesDTO();

        /*Размер коллекции передаем, для корректного имитирования поведения реального объекта
        (при пагинации указывается общее количество элементов, для определения количества страниц*/
        Page<MovieDTO> movieDTOPage = new PageImpl<>(movieDTOs, PageRequest.of(0, 2), movieDTOs.size());

        /*Мокаем сервисный метод*/
        when(movieService.getAll(any())).thenReturn(movieDTOPage);

        /*Выполняем запрос и проверяем результат*/
        mockMvc.perform(get("/api/movies")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].name").value("Фильм1"))
                .andExpect(jsonPath("$.content[0].description").value("Описание1"))
                .andExpect(jsonPath("$.content[1].name").value("Фильм2"))
                .andExpect(jsonPath("$.content[1].description").value("Описание2"));
    }

    @Test
    void getMoviesById() throws Exception {

        var movieDTO = createMovesDTO().get(0);
        when(movieService.getById(1L)).thenReturn(movieDTO);
        mockMvc.perform(get("/api/movies/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Фильм1"))
                .andExpect(jsonPath("$.description").value("Описание1"))
                .andExpect(jsonPath("$.duration").value(120.5))
                .andExpect(jsonPath("$.general_assessment").value(8.1))
                .andExpect(jsonPath("$.year_show").value("2024"));
        verify(movieService, times(1)).getById(1L);
    }

    @Test
    void getMoviesByGenre() throws Exception {

        var movie1 = createMoves().get(0);
        var movie2 = createMoves().get(1);

        List<Movie> movies = Arrays.asList(movie1, movie2);

        /* Настраиваем mock для метода findByGenre, чтобы он возвращал список фильмов */
        when(movieService.findByGenre(1L)).thenReturn(movies);

        /* Выполняем GET-запрос и проверяем результаты */
        mockMvc.perform(get("/api/movies/by-genre/{genre_id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Фильм1"))
                .andExpect(jsonPath("$[0].description").value("Описание1"))
                .andExpect(jsonPath("$[0].duration").value(120.5))
                .andExpect(jsonPath("$[0].general_assessment").value(8.1))
                .andExpect(jsonPath("$[0].year_show").value("2024"))
                .andExpect(jsonPath("$[1].name").value("Фильм2"))
                .andExpect(jsonPath("$[1].description").value("Описание2"))
                .andExpect(jsonPath("$[1].duration").value(90.0))
                .andExpect(jsonPath("$[1].general_assessment").value(7.5))
                .andExpect(jsonPath("$[1].year_show").value("2023"));
    }

    @Test
    void create() throws Exception {

        var movieDTO = createMovesDTO().get(0);

        /*Мокаем сервисный метод save*/
        when(movieService.save(any(Movie.class))).thenReturn(movieDTO);

        /*Преобразуем объект Movie в JSON-строку*/
        String movieJson = objectMapper.writeValueAsString(movieDTO);

        /*Выполняем POST-запрос и проверяем результаты*/
        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Фильм1"))
                .andExpect(jsonPath("$.description").value("Описание1"))
                .andExpect(jsonPath("$.duration").value(120.5))
                .andExpect(jsonPath("$.general_assessment").value(8.1))
                .andExpect(jsonPath("$.year_show").value("2024"));
    }

    @Test
    void update() throws Exception {

        var movie = createMoves().get(0);

        movie.setName("Обновлённый фильм");

        /* Преобразуем объект Movie в JSON-строку */
        ObjectMapper objectMapper = new ObjectMapper();
        String movieJson = objectMapper.writeValueAsString(movie);

        /* Настраиваем mock для метода update, чтобы он возвращал обновлённый фильм */
        when(movieService.update(movie.getId(), movie)).thenReturn(movie);

        /* Выполняем PUT-запрос и проверяем результаты */
        mockMvc.perform(put("/api/movies/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Обновлённый фильм"))
                .andExpect(jsonPath("$.description").value("Описание1"))
                .andExpect(jsonPath("$.duration").value(120.5))
                .andExpect(jsonPath("$.general_assessment").value(8.1))
                .andExpect(jsonPath("$.year_show").value("2024"));

    }

    @Test
    void deletes() throws Exception {
        var movie = createMoves().get(0);

        /*Выполняем DELETE-запрос для удаления фильма*/
        mockMvc.perform(delete("/api/movies/{id}", movie.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    private List<Movie> createMoves() {

        var firstMovie = new Movie(1L, "Фильм1", "Описание1", 120.5, 8.1,
                "2024", new HashSet<>(), new HashSet<>());
        var secondMovie = new Movie(2L, "Фильм2", "Описание2", 90.0, 7.5,
                "2023", new HashSet<>(), new HashSet<>());
        var threeMovie = new Movie(3L, "Фильм3", "Описание3", 110.0, 8.7,
                "2022", new HashSet<>(), new HashSet<>());
        var fourthMovie = new Movie(4L, "Фильм4", "Описание4", 95.5, 6.9,
                "2021", new HashSet<>(), new HashSet<>());

        return List.of(firstMovie, secondMovie, threeMovie, fourthMovie);
    }

    private List<MovieDTO> createMovesDTO() {
        var firstMovieDTO = new MovieDTO("Фильм1", "Описание1", 120.5,
                8.1, "2024", new HashSet<>());
        var secondDTO = new MovieDTO("Фильм2", "Описание2", 90,
                7.5, "2023", new HashSet<>());

        return List.of(firstMovieDTO, secondDTO);
    }
}