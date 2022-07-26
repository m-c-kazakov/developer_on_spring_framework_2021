package com.otus.homework.book_catalog_with_mongodb.service

import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book
import org.springframework.data.domain.PageRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface BookService {

    fun findAll(): Flux<Book>

    fun findById(id: String): Mono<Book>

    fun add(dto: BookDtoToCreate):  Mono<Book>

    fun update(dto: BookDtoToUpdate):  Mono<Book>

    fun deleteById(id: String): Mono<Result<Unit>>
}