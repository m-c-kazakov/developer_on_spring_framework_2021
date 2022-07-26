package com.otus.homework.book_catalog_with_mongodb.service

import com.otus.homework.book_catalog_with_mongodb.repository.BookDao
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.*
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
    override fun findAll(): List<Book> {
        return bookDao.findAll()
    }

    @Transactional(readOnly = true)
    override fun findAll(offset: Int, limit: Int): List<Book> {
        val pageRequest = PageRequest.of(offset, limit, Sort.Direction.DESC, "id")
        return bookDao.findAll(pageRequest).content
    }

    @Transactional(readOnly = true)
    override fun findById(id: String): Book {
        return bookDao
            .findById(id)
            .orElseThrow()
    }

    override fun add(dto: BookDtoToCreate): Book {
        val book = book {
            bookName = dto.name
            author = dto.author
            genre = dto.genre
            bookComments = dto.bookComments
        }
        return bookDao.save(book)
    }

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

    override fun deleteById(id: String): Result<Unit> {
        return Result.runCatching { bookDao.deleteById(id) }
    }
}