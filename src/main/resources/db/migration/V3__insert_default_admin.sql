insert into users (username, password, enabled)
values ('${default-admin-username}', '${default-admin-password}', true);

insert into authorities (username, authority) values ('${default-admin-username}', 'ROLE_ADMIN');