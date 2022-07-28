package com.otus.homework.repository.changelog.mongo

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.mongodb.client.MongoDatabase
import com.otus.homework.model.mongo.BookMongo
import com.otus.homework.repository.BookMongoRepository

@ChangeLog(order = "001")
class MongoInitDataChangeLog() {

    @ChangeSet(order = "001", id = "dropDB", author = "kazakov", runAlways = true)
    fun dropDB(database: MongoDatabase) {
        database.drop()
    }

    @ChangeSet(order = "002", id = "insert books", author = "kazakov", runAlways = true)
    fun populateData(repository: BookMongoRepository) {
        val bookMongo1 = BookMongo("name1", "author1", "genre1", mutableListOf("comment1"))
        val bookMongo2 = BookMongo("name2", "author1", "genre2", mutableListOf("comment2"))
        val bookMongo3 = BookMongo("name3", "author2", "genre2", mutableListOf("comment3"))

        repository.saveAll(mutableListOf(bookMongo1, bookMongo2, bookMongo3))
    }
}