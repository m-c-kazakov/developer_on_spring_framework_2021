package com.otus.homework.book_catalog_with_mongodb.dao

import com.otus.homework.book_catalog_with_mongodb.integration.IntegrationTestBased
import com.otus.homework.book_catalog_with_mongodb.model.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class BookRepositoryTest : IntegrationTestBased() {

    @Autowired
    lateinit var bookRepository: BookRepository


    @Test
    fun `вывести список авторов`() {
        val book = Book("name", "author1", "genre", mutableListOf("Comment"))
        bookRepository.save(book)
        val book1 = Book("name", "author2", "genre", mutableListOf("Comment"))
        bookRepository.save(book1)

        val allAuthors = bookRepository.findAllAuthors()
        assertThat(allAuthors).hasSize(2)
        assertThat(allAuthors.map { it.author }).containsAll(mutableListOf("author1", "author2"))
    }


    @Test
    fun `вывести список жанров`() {
        val book = Book("name", "author1", "genre1", mutableListOf("Comment"))
        bookRepository.save(book)
        val book1 = Book("name", "author2", "genre2", mutableListOf("Comment"))
        bookRepository.save(book1)

        val allAuthors = bookRepository.findAllGenre()
        assertThat(allAuthors).hasSize(2)
        assertThat(allAuthors.map { it.genre }).containsAll(mutableListOf("genre1", "genre2"))
    }


    @Test
    fun `сделать поиск по автору`() {
        val book1 = Book("name", "author3", "genre2", mutableListOf("Comment"))
        val book = bookRepository.save(book1)

        assertThat(bookRepository.findByAuthor("author3")).isEqualTo(book)
    }


    @Test
    fun `сделаем редактирование авторов`() {

        val newAuthorName = "author9999"
        val book = bookRepository.save(Book("name", "author4", "genre2", mutableListOf("Comment")))
        with(book) {
            this.author = newAuthorName
        }
        bookRepository.save(book)
        assertThat(bookRepository.findById(book.id).orElseThrow()).extracting { book.author }.isEqualTo(newAuthorName)
    }
}