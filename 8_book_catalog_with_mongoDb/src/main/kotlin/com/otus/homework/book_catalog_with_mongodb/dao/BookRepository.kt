package com.otus.homework.book_catalog_with_mongodb.dao

import com.otus.homework.book_catalog_with_mongodb.model.Book
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface BookRepository : MongoRepository<Book, String> {

    @Query(value = "{ }", fields = "{'_id': 0, 'author' : 1}", )
    fun findAllAuthors() : List<Book>

    @Query(value = "{ }", fields = "{'_id': 0, 'genre' : 1}", )
    fun findAllGenre() : List<Book>

    fun findByAuthor(author: String) : Book



}