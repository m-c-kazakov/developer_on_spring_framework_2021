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
        val book = bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))
        assertThat(bookService.findById(book.id))
            .usingRecursiveComparison()
            .isEqualTo(book)
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

    @Test
    fun updateAuthorName() {
        val book1 = bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))
        val book2 = bookService.add(BookDtoToCreate("name", "author", "genre", mutableListOf("Comment")))

        bookService.updateAuthorName("author", "qwerty")

        assertThat(bookService.findById(book1.id)).extracting { it.author }.isEqualTo("qwerty")
        assertThat(bookService.findById(book2.id)).extracting { it.author }.isEqualTo("qwerty")

    }
}