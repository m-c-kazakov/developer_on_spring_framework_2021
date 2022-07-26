package com.otus.homework.book_catalog_with_mongodb.service

import com.otus.homework.book_catalog_with_mongodb.dao.BookDao
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book
import com.otus.homework.book_catalog_with_mongodb.model.book
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@Transactional
class BookServiceImpl(
    val bookDao: BookDao,
) : BookService {

    @Transactional(readOnly = true)
    override fun findAll(): Flux<Book> {
        return bookDao.findAll()
    }

    @Transactional(readOnly = true)
    override fun findById(id: String): Mono<Book> {
        return bookDao.findById(id)
    }

    override fun add(dto: BookDtoToCreate): Mono<Book> {
        val book = book {
            bookName = dto.name
            author = dto.author
            genre = dto.genre
            bookComments = dto.bookComments
        }

        return bookDao.save(book)
    }

    override fun update(dto: BookDtoToUpdate): Mono<Book> {

        return findById(dto.id)
            .map {
                it.apply {
                    this.name = dto.name
                    this.author = dto.author
                    this.genre = dto.genre
                    this.bookComments = listOf(dto.bookComments, it.bookComments)
                        .flatten()
                        .distinct()
                }
            }
            .flatMap {
                bookDao.save(it)
            }

    }

    override fun deleteById(id: String): Mono<Result<Unit>> {
        return Mono.fromRunnable { Result.runCatching { bookDao.deleteById(id) } }
    }
}