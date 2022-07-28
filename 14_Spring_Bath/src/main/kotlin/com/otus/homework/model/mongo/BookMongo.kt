package com.otus.homework.model.mongo

import org.springframework.data.mongodb.core.mapping.Document

@Document("books")
data class BookMongo(
    var name: String,
    var author: String,
    var genre: String,
    var bookComments: MutableList<String>
) : UniqueIdentifier() {
}

