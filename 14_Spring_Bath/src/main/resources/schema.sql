DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS BOOK_COMMENTS;

create table authors
(
    id   serial primary key,
    name varchar(255) not null
);

create table genres
(
    id   serial primary key,
    name varchar(255) not null
);

create table books
(
    id        serial primary key,
    name      varchar(255) not null,
    author_id integer      not null,
    genre_id  integer      not null,
    foreign key (author_id) references authors(id),
    foreign key (genre_id) references genres(id)
);

create table book_comments
(
    id      serial primary key,
    name    varchar(255) not null,
    book_id integer not null,
    foreign key (book_id) references books (id) on delete cascade
);