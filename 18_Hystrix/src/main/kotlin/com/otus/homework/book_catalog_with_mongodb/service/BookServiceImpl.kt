package com.otus.homework.book_catalog_with_mongodb.service

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.otus.homework.book_catalog_with_mongodb.dao.BookDao
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book
import com.otus.homework.book_catalog_with_mongodb.model.book
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class BookServiceImpl(
    val bookDao: BookDao,
) : BookService {

    @Transactional(readOnly = true)
    @HystrixCommand(groupKey = "BookService", commandKey = "findAll")
    override fun findAll(): List<Book> {
        return bookDao.findAll()
    }

    @Transactional(readOnly = true)
    @HystrixCommand(groupKey = "BookService", commandKey = "findAll")
    override fun findAll(offset: Int, limit: Int): List<Book> {
        val pageRequest = PageRequest.of(offset, limit, Sort.Direction.DESC, "id")
        return bookDao.findAll(pageRequest).content
    }

    @Transactional(readOnly = true)
    @HystrixCommand(groupKey = "BookService", commandKey = "findById")
    override fun findById(id: String): Book {
        return bookDao
            .findById(id)
            .orElseThrow()
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "add")
    override fun add(dto: BookDtoToCreate): Book {
        val book = book {
            bookName = dto.name
            author = dto.author
            genre = dto.genre
            bookComments = dto.bookComments
        }
        return bookDao.save(book)
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "update")
    override fun update(dto: BookDtoToUpdate): Book {


        val book = findById(dto.id).apply {
            this.name = dto.name
            this.author = dto.author
            this.genre = dto.genre
            this.bookComments = listOf(dto.bookComments, this.bookComments)
                .flatten()
                .distinct()
        }
        return bookDao.save(book)
    }

    @HystrixCommand(groupKey = "BookService", commandKey = "deleteById")
    override fun deleteById(id: String): Result<Unit> {
        return Result.runCatching { bookDao.deleteById(id) }
    }
}