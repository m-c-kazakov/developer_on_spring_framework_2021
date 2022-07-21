package com.otus.homework.book_catalog_with_mongodb.dao

import com.otus.homework.book_catalog_with_mongodb.model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface BookDao : MongoRepository<Book, String> {

    override fun findAll(pageable: Pageable): Page<Book>

}