package com.otus.homework.batch

import com.otus.homework.model.mongo.BookMongo
import com.otus.homework.model.postgres.BookPostgres
import com.otus.homework.repository.BookPostgresRepository
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
open class PostgresBookWriter(
    private val repository: BookPostgresRepository,
) : ItemWriter<BookMongo> {


    @Transactional
    override fun write(items: MutableList<out BookMongo>) {
        val booksPostgres = items.map {
            return@map BookPostgres(
                name = it.name,
                author = it.author,
                genre = it.genre,
                bookComments = it.bookComments
            )
        }
        repository.saveAll(booksPostgres)
    }
}