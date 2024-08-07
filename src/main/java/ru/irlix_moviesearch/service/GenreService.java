package ru.irlix_moviesearch.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.irlix_moviesearch.dto.GenreDTO;
import ru.irlix_moviesearch.model.Genre;
import ru.irlix_moviesearch.repository.GenreRepository;
import ru.irlix_moviesearch.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    public final MovieRepository movieRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<GenreDTO> findAll() {
        List<GenreDTO> genreDTOs = new ArrayList<>();
        genreRepository
                .findAll()
                .forEach(genre -> genreDTOs
                        .add(modelMapper.map(genre, GenreDTO.class)));

        return genreDTOs;
    }

    public GenreDTO getById(Long id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        return convertToDTO(genre);
    }

    public GenreDTO create(Genre genre) {
        Genre genre1 = genreRepository.save(genre);
        return convertToDTO(genre1);
    }

    public Genre update(Long id, Genre genre) {
        Genre updateGenre = genreRepository.findById(id).orElse(null);
        updateGenre.setName(genre.getName());
        updateGenre.setDescription(genre.getDescription());

        return genreRepository.save(updateGenre);
    }

    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

    public GenreDTO convertToDTO(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }
}
