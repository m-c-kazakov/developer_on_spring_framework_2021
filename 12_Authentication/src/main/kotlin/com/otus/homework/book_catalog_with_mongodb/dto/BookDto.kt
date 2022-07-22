package com.otus.homework.book_catalog_with_mongodb.dto

data class BookDto(
    val id:String,
    val name: String,
    val author: String,
    val genre: String,
    val bookComments: List<String>
)
