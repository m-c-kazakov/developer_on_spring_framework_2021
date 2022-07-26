package com.otus.homework.book_catalog_with_mongodb.security.model

import com.otus.homework.book_catalog_with_mongodb.model.UniqueIdentifier

data class User(
    val username: String,
    val password: String,
    val roles: MutableCollection<Role> = mutableListOf(),
    var enabled: Boolean = true
) : UniqueIdentifier()
