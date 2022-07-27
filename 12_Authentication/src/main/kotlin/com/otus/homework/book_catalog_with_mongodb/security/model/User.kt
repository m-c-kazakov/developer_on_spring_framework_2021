package com.otus.homework.book_catalog_with_mongodb.security.model

import com.otus.homework.book_catalog_with_mongodb.model.UniqueIdentifier
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    val userName: String,
    val password: String,
    val roles: MutableCollection<Role> = mutableListOf(Role.USER),
    var enabled: Boolean = true
) : UniqueIdentifier()
