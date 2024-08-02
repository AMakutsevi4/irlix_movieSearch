CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(15),
                        description VARCHAR(255),
                        duration DOUBLE PRECISION,
                        general_assessment DOUBLE PRECISION,
                        year_show VARCHAR(4),
                        reviews TEXT
);

CREATE TABLE genres (
                        id SERIAL PRIMARY KEY,
                        name varchar(10),
                        description VARCHAR(255)
);

CREATE TABLE movie_genres (
                              movie_id INTEGER,
                              genre_id INTEGER,
                              FOREIGN KEY (movie_id) REFERENCES movies(id),
                              FOREIGN KEY (genre_id) REFERENCES genres(id)

);
