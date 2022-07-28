package ru.revseev.library.repo.changelog.mongo

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.mongodb.client.MongoDatabase
import com.otus.homework.batch.model.mongo.Book
import com.otus.homework.book_catalog_with_mongodb.dao.BookMongoRepository

@ChangeLog(order = "001")
class MongoInitDataChangeLog {

    @ChangeSet(order = "001", id = "dropDB", author = "revseev", runAlways = true)
    fun dropDB(database: MongoDatabase) {
        database.drop()
    }

    @ChangeSet(order = "002", id = "insert books", author = "revseev", runAlways = true)
    fun populateData(repository: BookMongoRepository) {
        val book1 = Book("name1", "author1", "genre1", mutableListOf("comment1"))
        val book2 = Book("name2", "author1", "genre2", mutableListOf("comment2"))
        val book3 = Book("name3", "author2", "genre2", mutableListOf("comment3"))

        repository.saveAll(mutableListOf(book1, book2, book3))
        print("asfd")


    }
}