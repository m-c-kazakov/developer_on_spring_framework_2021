package com.otus.homework.book_catalog_with_mongodb.service

import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book

interface BookService {

    fun findAll(): List<Book>

    fun findById(id: String): Book

    fun add(dto: BookDtoToCreate): Book

    fun update(dto: BookDtoToUpdate): Book

    fun deleteById(id: String)
}