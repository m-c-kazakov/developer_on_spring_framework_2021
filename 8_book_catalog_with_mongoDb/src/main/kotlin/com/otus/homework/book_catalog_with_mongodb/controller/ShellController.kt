package com.otus.homework.book_catalog_with_mongodb.controller

import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book
import com.otus.homework.book_catalog_with_mongodb.service.BookService
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class ShellController(val bookService: BookService) {

    @ShellMethod(key = ["getAllBook", "all"], value = "get all books")
    fun getAllBook() : List<Book> {
        return bookService.findAll()
    }

    @ShellMethod(key = ["getBookById", "gbbid"], value = "get book by id")
    fun getBookById(@ShellOption booksId: String): Book {
        return bookService.findById(booksId)
    }

    @ShellMethod(key = ["createBook", "cb"], value = "create book")
    fun createBook(@ShellOption book: BookDtoToCreate): Book {
        return bookService.add(book)
    }

    @ShellMethod(key = ["updateBook", "ub"], value = "update book")
    fun updateBook(@ShellOption book: BookDtoToUpdate) {
        bookService.update(book)
    }

    @ShellMethod(key = ["removeBook", "rb"], value = "remove book")
    fun remove(@ShellOption bookId: String) {
        bookService.deleteById(bookId)
    }

    @ShellMethod(key = ["findAllAuthors", "faa"], value = "find All Authors")
    fun findAllAuthors(): List<String> {
        return bookService.findAllAuthors()
    }

    @ShellMethod(key = ["findAllGenre", "fag"], value = "find All Genre")
    fun findAllGenre(): List<String> {
        return bookService.findAllGenre()
    }

    @ShellMethod(key = ["findByAuthor", "fba"], value = "find By Author")
    fun findByAuthor(@ShellOption author: String): Book {
        return bookService.findByAuthor(author)
    }

    @ShellMethod(key = ["updateAuthorName", "uan"], value = "update Author Name")
    fun updateAuthorName(@ShellOption bookId: String, @ShellOption author: String): Book {
        return bookService.updateAuthorName(bookId, author)
    }
}