package com.otus.homework.book_catalog_with_mongodb.security.repository

import com.otus.homework.book_catalog_with_mongodb.security.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface UserRepository : MongoRepository<User, String>{

    fun findUserByUserName(userName: String) : Optional<User>
}