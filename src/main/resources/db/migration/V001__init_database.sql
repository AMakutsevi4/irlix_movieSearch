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

CREATE TABLE critics (
                        id SERIAL PRIMARY KEY,
                        first_name VARCHAR(10) NOT NULL,
                        last_name VARCHAR(10) NOT NULL,
                        info VARCHAR(70)
);

CREATE TABLE reviews (

                        id SERIAL PRIMARY KEY,
                        rating DOUBLE PRECISION,
                        message VARCHAR(255),
                        date TIMESTAMP,
                        movie_id INT,
                        critic_id INT,
                        FOREIGN KEY (movie_id) REFERENCES movies(id),
                        FOREIGN KEY (critic_id) REFERENCES critics(id)
);

