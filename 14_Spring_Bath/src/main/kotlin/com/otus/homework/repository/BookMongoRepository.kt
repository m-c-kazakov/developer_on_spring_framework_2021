package com.otus.homework.repository

import com.otus.homework.model.mongo.BookMongo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface BookMongoRepository : MongoRepository<BookMongo, String> {

    override fun findAll(pageable: Pageable): Page<BookMongo>

}