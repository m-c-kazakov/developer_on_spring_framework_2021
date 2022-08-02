package com.otus.homework.book_catalog_with_mongodb.model

import org.springframework.data.mongodb.core.mapping.Document
@Document("books")
data class Book(
    var name: String,
    var author: String,
    var genre: String,
    var bookComments: List<String>
) : UniqueIdentifier() {
}


fun book(function: BookDsl.() -> Unit) : Book = BookDsl().apply(function).build()

class BookDsl {
    lateinit var bookName: String
    lateinit var author: String
    lateinit var genre: String
    lateinit var bookComments: List<String>

    fun build(): Book = Book(bookName, author, genre, bookComments)

}

