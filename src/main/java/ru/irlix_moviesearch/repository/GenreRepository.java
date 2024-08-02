package ru.irlix_moviesearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.irlix_moviesearch.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
