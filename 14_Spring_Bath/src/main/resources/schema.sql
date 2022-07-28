DROP TABLE IF EXISTS BOOKS;

create table books
(
    id            serial primary key,
    name          varchar(255) not null,
    author        varchar(255) not null,
    genre         varchar(255) not null,
    book_comments text[] not null
);
