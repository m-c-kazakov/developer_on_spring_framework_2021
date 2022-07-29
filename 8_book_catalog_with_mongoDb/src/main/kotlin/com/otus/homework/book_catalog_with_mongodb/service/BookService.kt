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

    fun findAllAuthors():List<String>

    fun findAllGenre():List<String>

    fun findByAuthor(author: String): List<Book>

    fun updateAuthorName(currentName:String, updateName:String): List<Book>

}