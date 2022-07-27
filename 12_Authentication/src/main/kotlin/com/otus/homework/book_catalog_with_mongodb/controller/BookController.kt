package com.otus.homework.book_catalog_with_mongodb.controller

import com.otus.homework.book_catalog_with_mongodb.dto.BookDto
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book
import com.otus.homework.book_catalog_with_mongodb.service.BookService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
class BookController(val bookService: BookService){
    private val log: Logger = LoggerFactory.getLogger(BookService::class.java)


    @GetMapping("/api/v1/books")
    fun findAll(
        @RequestParam(defaultValue = "0") offset: Int,
        @RequestParam(defaultValue = "10") limit: Int,
    ): List<BookDto> {
        log.info(">>GET: /api/v1/books : offset=$offset, limit=$limit")

        return bookService.findAll(offset, limit).map { mapTo(it)}
    }

    @GetMapping("/api/v1/books/{id}")
    fun findById(@PathVariable id: String): BookDto {
        log.info(">>GET: /api/v1/books/$id")
        return mapTo(bookService.findById(id))
    }

    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody bookDtoToCreate: BookDtoToCreate): BookDto {
        log.info(">>POST: /api/v1/books : $bookDtoToCreate")
        return mapTo(bookService.add(bookDtoToCreate))
    }

    private fun mapTo(book: Book) =
        BookDto(book.id, book.name, book.author, book.genre, book.bookComments)

    @PutMapping("/api/v1/books")
    fun update(@RequestBody bookDtoToUpdate: BookDtoToUpdate): BookDto {
        log.info(">>PUT: /api/v1/books/ : $bookDtoToUpdate")
        return mapTo(bookService.update(bookDtoToUpdate))
    }

    @DeleteMapping("/api/v1/books/{id}")
    fun deleteById(@PathVariable id: String): ResponseEntity<*> {
        log.info(">>DELETE: /api/v1/books/$id")
        val result = bookService.deleteById(id)
        log.info("DELETE result: $result")
        return when {
            result.isSuccess -> ResponseEntity.ok()
            result.isFailure -> ResponseEntity.notFound()
            else -> ResponseEntity.badRequest()
        }.build<Nothing>()
    }
}
