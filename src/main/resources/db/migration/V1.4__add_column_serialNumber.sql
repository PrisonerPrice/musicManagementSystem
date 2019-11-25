ALTER TABLE stock
ADD serial_num INT;

UPDATE stock
SET serial_num = 1 WHERE id = 1;
UPDATE stock
SET serial_num = 2 WHERE id = 2;
UPDATE stock
SET serial_num = 3 WHERE id = 3;
UPDATE stock
SET serial_num = 4 WHERE id = 4;
UPDATE stock
SET serial_num = 5 WHERE id = 5;
UPDATE stock
SET serial_num = 6 WHERE id = 6;
UPDATE stock
SET serial_num = 7 WHERE id = 7;
UPDATE stock
SET serial_num = 8 WHERE id = 8;
UPDATE stock
SET serial_num = 9 WHERE id = 9;
COMMIT;

