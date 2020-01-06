insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/albums,/stocks,/artists', 'Y', 'Y', 'Y', 'N'),
('user', '/albums,/stocks', 'Y', 'N', 'N', 'N')
;
commit;

insert into users (name, password, first_name, last_name, email) values
('hanWang', '123456', 'Han', 'Wang', 'hanwang@hotmail.com'),
('jason', '11112222', 'Jason', 'Black', 'jb@jb.com'),
('Dio', 'alpha', 'Dio', 'White', 'dio_black@gmail.com')
;
commit;

insert into users_role values
(1, 1),
(2, 2),
(3, 3),
(1, 2),
(1, 3)
;
commit;