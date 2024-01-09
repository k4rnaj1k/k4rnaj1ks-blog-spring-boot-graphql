create table author
(
    id         integer generated always as identity primary key not null,
    first_name varchar(255),
    username   varchar(255)
);

create table blog
(
    id        integer generated always as identity primary key not null,
    content   text,
    title     varchar(255),
    author_id integer,
    foreign key (author_id) references author (id)
);

