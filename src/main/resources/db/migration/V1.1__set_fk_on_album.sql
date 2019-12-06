ALTER TABLE album ADD CONSTRAINT
album_fk
FOREIGN KEY(artist_id) REFERENCES artist (id);