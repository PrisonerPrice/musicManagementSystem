INSERT INTO album (name, release_year, artist, genre, description) VALUES
('Alvvays', 2014, 'Alvvays', 'Alternative', 'First album of Alvvays'),
('Rubber Soul', 1965, 'The Beatles', 'Rock', 'Something special'),
('Doo-Wops and Hooligans', 2010, 'Bruno Mars', 'Pop', 'You must play the album on your wedding'),
('Monkey Business', 2005, 'The Black Eyed Peas', 'Hip-Hop', 'Shake your body!'),
('Parachutes', 2000, 'Coldplay', 'Rock', 'This one will warm your heart'),
('November''s Chopin', 2005, 'Jay Chou', 'Mandopop', 'You must hear'),
('Mylo Xyloto', 2011, 'Coldplay', 'Pop', 'Not bad'),
('New', 2017, 'Jabberloop', 'Jazz', 'My favorite'),
('Bach Concertos', 2003, 'Hilary Hahn', 'Classic', NULL)
;
COMMIT;

INSERT INTO artist (name, start_year, end_year, description) VALUES
('Alvvays', 2014, NULL,'A canadian band'),
('The Beatles', 1960, 1970, 'Maybe the greatest band ever in the world'),
('The Black Eyed Peas', 2003, NULL, NULL),
('Coldplay', 1999, NULL, 'My favorite'),
('Jay Chou', 2000, NULL, 'Affect a generation'),
('Jabberloop', 2005, NULL, 'Jazz from Japan'),
('Linkin Park', 1999, NULL, 'Hardcore')
;
COMMIT;

DROP TABLE stock;
CREATE TABLE stock (
    id SERIAL NOT NULL,
    album_name VARCHAR(100) NOT NULL,
    stock_NY_01 INT,
    stock_NY_02 INT,
    stock_DC_01 INT,
    stock_VA_01 INT,
    stock_MD_01 INT
);
ALTER TABLE stock ADD CONSTRAINT stock_pk PRIMARY KEY (id);

INSERT INTO stock (album_name, stock_NY_01, stock_NY_02, stock_DC_01, stock_VA_01, stock_MD_01) VALUES
('Alvvays', 30, 20, 15, 0, 3),
('Rubber Soul', 10, 2, 2, 4, 7),
('Doo-Wops and Hooligans',21 , 2, 15, 7, 8),
('Monkey Business', 99, 56, 32, 12, 5),
('Parachutes', 187, 234, 88, 76, 42),
('November''s Chopin', 45, 23, 33, 12, 7),
('Mylo Xyloto', 12, 5, 7, 9, 9),
('New', 14, 3, 2, 1, 1),
('Bach Concertos', 3, 2, 0, 0, 0)
;
COMMIT;



