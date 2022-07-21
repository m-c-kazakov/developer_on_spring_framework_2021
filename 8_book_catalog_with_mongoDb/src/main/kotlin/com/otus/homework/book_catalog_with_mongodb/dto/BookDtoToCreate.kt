package com.otus.homework.book_catalog_with_mongodb.dto

data class BookDtoToCreate(
    val name: String,
    val author: String,
    val genre: String,
    val bookComments: MutableList<String> = mutableListOf()
)