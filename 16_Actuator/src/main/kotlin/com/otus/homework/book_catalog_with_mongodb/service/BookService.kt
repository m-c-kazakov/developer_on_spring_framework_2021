package com.otus.homework.book_catalog_with_mongodb.service

import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book
import org.springframework.data.domain.PageRequest

interface BookService {

    fun findAll(): List<Book>

    fun findAll(offset: Int, limit: Int): List<Book>

    fun findById(id: String): Book

    fun add(dto: BookDtoToCreate): Book

    fun update(dto: BookDtoToUpdate): Book

    fun deleteById(id: String): Result<Unit>
}