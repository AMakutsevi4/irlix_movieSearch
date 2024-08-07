
INSERT INTO genres (name, description)
VALUES ('Драма', 'Драматические фильмы');
INSERT INTO genres (name, description)
VALUES ('Боевик', 'Фильмы с экшеном');
INSERT INTO genres (name, description)
VALUES ('Комедия', 'Комедийные фильмы');
INSERT INTO genres (name, description)
VALUES ('Ужасы', 'Фильмы ужасов');


INSERT INTO movies (name, description, duration, general_assessment, year_show, reviews)
VALUES ('Фильм1', 'Описание1', 120.5, 8.1, '2024', 'Отличный фильм');
INSERT INTO movies (name, description, duration, general_assessment, year_show, reviews)
VALUES ('Фильм2', 'Описание2', 90.0, 7.5, '2023', 'Хороший фильм');
INSERT INTO movies (name, description, duration, general_assessment, year_show, reviews)
VALUES ('Фильм3', 'Описание3', 110.0, 8.7, '2022', 'Классный фильм');
INSERT INTO movies (name, description, duration, general_assessment, year_show, reviews)
VALUES ('Фильм4', 'Описание4', 95.5, 6.9, '2021', 'Плохой фильм');


INSERT INTO movie_genres (movie_id, genre_id)
VALUES (1, 1);
INSERT INTO movie_genres (movie_id, genre_id)
VALUES (1, 2);
INSERT INTO movie_genres (movie_id, genre_id)
VALUES (2, 2);
INSERT INTO movie_genres (movie_id, genre_id)
VALUES (2, 3);
INSERT INTO movie_genres (movie_id, genre_id)
VALUES (3, 1);
INSERT INTO movie_genres (movie_id, genre_id)
VALUES (4, 4);


INSERT INTO critics (first_name, last_name, info)
VALUES ('Александр', 'М', 'Опытный кинокритик');
INSERT INTO critics (first_name, last_name, info)
VALUES ('Алексей', 'К', 'Известный кинокритик');
INSERT INTO critics (first_name, last_name, info)
VALUES ('Петр', 'П', 'Кинокритик журнала кинопоиск');
INSERT INTO critics (first_name, last_name, info)
VALUES ('Василий', 'В', 'Блогер о кино');


INSERT INTO reviews (rating, message, date, movie_id, critic_id)
VALUES (8.0, 'Удивительный сюжет', '2024-08-10 12:34:56', 1, 1);
INSERT INTO reviews (rating, message, date, movie_id, critic_id)
VALUES (7.5, 'Хорошая режиссура', '2024-08-15 12:34:56', 1, 2);
INSERT INTO reviews (rating, message, date, movie_id, critic_id)
VALUES (8.2, 'Отличные экшен сцены', '2024-08-20 12:34:56', 2, 3);
INSERT INTO reviews (rating, message, date, movie_id, critic_id)
VALUES (6.8, 'Могло быть лучше', '2024-08-25 12:34:56', 3, 4);
INSERT INTO reviews (rating, message, date, movie_id, critic_id)
VALUES (9.0, 'Шедевр', '2024-08-30 12:34:56', 4, 1);