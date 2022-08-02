--liquibase formatted sql

--changeset kazakov:1
create table books
(
    id            serial primary key,
    name          varchar(255) not null,
    author        varchar(255) not null,
    genre         varchar(255) not null,
    book_comments text[] not null
);


