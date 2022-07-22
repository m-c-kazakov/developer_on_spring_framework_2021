package com.otus.homework.book_catalog_with_mongodb.service

import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.integration.IntegrationTestBased
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class BookServiceImplTest : IntegrationTestBased() {

    @Autowired
    lateinit var bookService: BookService


    @Test
    fun getAll() {
        bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))
        assertThat(bookService.findAll()).hasSize(1)
    }

    @Test
    fun getById() {
        val book = bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))
        assertThat(bookService.findById(book.id))
            .usingRecursiveComparison()
            .isEqualTo(book)
    }

    @Test
    fun add() {
        bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))
        bookService.add(BookDtoToCreate("name2", "author2", "genre2", mutableListOf("Comment2")))
        assertThat(bookService.findAll()).isNotEmpty
    }

    @Test
    fun update() {
        val book = bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))
        val newName = "NewName"
        val updatedBook = bookService.update(
            BookDtoToUpdate(
                book.id,
                newName,
                book.author,
                book.genre,
                book.bookComments.toMutableList()
            )
        )

        assertThat(bookService.findById(updatedBook.id))
            .usingRecursiveComparison()
            .isEqualTo(updatedBook)
            .isNotEqualTo(book)
    }

    @Test
    fun deleteById() {

        val book = bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))
        bookService.deleteById(book.id)
        assertThrows<NoSuchElementException> { bookService.findById(book.id) }
    }
}