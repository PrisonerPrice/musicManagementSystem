ALTER TABLE stock ADD CONSTRAINT
stock_fk
FOREIGN KEY(album_id) REFERENCES album (id);