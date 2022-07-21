package com.otus.homework.book_catalog_with_mongodb.dao

import com.otus.homework.book_catalog_with_mongodb.model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface BookDao : ReactiveMongoRepository<Book, String> {

}