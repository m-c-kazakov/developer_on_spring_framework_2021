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
import reactor.core.publisher.Mono


@CrossOrigin
@RestController
class BookController(val bookService: BookService){
    private val log: Logger = LoggerFactory.getLogger(BookService::class.java)


    @GetMapping("/api/v1/books")
    fun findAll(): Mono<List<BookDto>> {
        log.info(">>GET: /api/v1/books")
        return bookService
            .findAll()
            .map { mapTo(it) }
            .collectList()
    }

    @GetMapping("/api/v1/books/{id}")
    fun findById(@PathVariable id: String): Mono<BookDto> {
        log.info(">>GET: /api/v1/books/$id")
        return bookService
            .findById(id)
            .map { mapTo(it) }
    }

    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody bookDtoToCreate: BookDtoToCreate): Mono<BookDto> {
        log.info(">>POST: /api/v1/books : $bookDtoToCreate")
        return bookService.add(bookDtoToCreate).map { mapTo(it) }
    }

    private fun mapTo(book: Book) =
        BookDto(book.id, book.name, book.author, book.genre, book.bookComments)

    @PutMapping("/api/v1/books")
    fun update(@RequestBody bookDtoToUpdate: BookDtoToUpdate): Mono<BookDto> {
        log.info(">>PUT: /api/v1/books/ : $bookDtoToUpdate")
        return bookService.update(bookDtoToUpdate).map { mapTo(it) }
    }

    @DeleteMapping("/api/v1/books/{id}")
    fun deleteById(@PathVariable id: String): Mono<ResponseEntity<*>> {
        log.info(">>DELETE: /api/v1/books/$id")
        return bookService.deleteById(id).map {
            return@map when {
                it.isSuccess -> ResponseEntity.ok()
                it.isFailure -> ResponseEntity.notFound()
                else -> ResponseEntity.badRequest()
            }.build<Nothing>()
        }

    }
}
