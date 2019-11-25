CREATE TABLE album (
    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    release_year INT NOT NULL,
    artist VARCHAR(100) NOT NULL,
    genre VARCHAR(30),
    description VARCHAR(255)
);
ALTER TABLE album ADD CONSTRAINT album_pk PRIMARY KEY (id);

CREATE TABLE artist (
    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    start_year INT NOT NULL,
    end_year INT,
    description VARCHAR(255)
);
ALTER TABLE artist ADD CONSTRAINT artist_pk PRIMARY KEY (id);

CREATE TABLE stock (
    id SERIAL NOT NULL,
    album_name VARCHAR(100) NOT NULL,
    stock INT
);
ALTER TABLE stock ADD CONSTRAINT stock_pk PRIMARY KEY (id);