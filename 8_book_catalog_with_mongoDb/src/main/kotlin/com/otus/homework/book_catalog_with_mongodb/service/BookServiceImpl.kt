package com.otus.homework.book_catalog_with_mongodb.service

import com.otus.homework.book_catalog_with_mongodb.dao.BookRepository
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToCreate
import com.otus.homework.book_catalog_with_mongodb.dto.BookDtoToUpdate
import com.otus.homework.book_catalog_with_mongodb.model.Book
import com.otus.homework.book_catalog_with_mongodb.model.book
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val mongoOperations: MongoOperations
) : BookService {

    @Transactional(readOnly = true)
    override fun findAll(): List<Book> {
        return bookRepository.findAll()
    }

    @Transactional(readOnly = true)
    override fun findById(id: String): Book {
        return bookRepository
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
        return bookRepository.save(book)
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
        return bookRepository.save(book)
    }

    override fun deleteById(id: String) {
        bookRepository.deleteById(id)
    }

    override fun findAllAuthors(): List<String> {

        return bookRepository
            .findAllAuthors()
            .map { it.author }
    }

    override fun findAllGenre(): List<String> {
        return bookRepository
            .findAllGenre()
            .map { it.genre }
    }

    override fun findByAuthor(author: String): List<Book> {
        return bookRepository.findByAuthor(author)
    }

    override fun updateAuthorName(currentName:String, updateName:String): List<Book> {

        val query = Query.query(
            Criteria
                .where("author")
                .isEqualTo(currentName)
        )

        val update = Update.update("author", updateName)



        mongoOperations.updateMulti(query, update, Class.forName("com.otus.homework.book_catalog_with_mongodb.model.Book"))
        return bookRepository.findByAuthor(updateName)

    }
}