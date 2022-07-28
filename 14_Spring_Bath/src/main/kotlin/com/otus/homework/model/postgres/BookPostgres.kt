package com.otus.homework.model.postgres

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("books")
data class BookPostgres(
    @Id
    var id: Long? = null,
    var name: String,
    var author: String,
    var genre: String,
    var bookComments: MutableList<String> = mutableListOf()
)
