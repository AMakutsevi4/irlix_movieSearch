INSERT INTO genres(name,description)
VALUES ('Ужасы','Ужасы');
INSERT INTO genres(name,description)
VALUES ('Комедия','Комедия');
INSERT INTO genres(name,description)
VALUES ('Триллер','Триллер');

INSERT INTO movies(name,description, duration, general_assessment, year_show, reviews)
VALUES ('А','Первый классный фильм', 11.1, 1.1,'2011','Первый фильм с категорией ужасы');
INSERT INTO movies(name,description, duration, general_assessment, year_show, reviews)
VALUES ('Б', 'Второй классный фильм', 22.2, 2.2,'2012','Второй фильм с категорией комедия');
INSERT INTO movies(name,description, duration, general_assessment, year_show, reviews)
VALUES ('В', 'Третий классный фильм', 33.3, 3.3,'2013','Третий фильм с категорией Триллер');
INSERT INTO movies(name,description, duration, general_assessment, year_show, reviews)
VALUES ('Г', 'Четвёртый классный фильм', 44.4, 4.4,'2014','Четвёртый фильм с категорией Триллер');

INSERT INTO movie_genres(genre_id, movie_id)
VALUES (1,1);
INSERT INTO movie_genres(genre_id, movie_id)
VALUES (2,2);
INSERT INTO movie_genres(genre_id, movie_id)
VALUES (3,3);

