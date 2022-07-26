package com.otus.homework.book_catalog_with_mongodb.security.service

import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.model.User
import java.util.*

interface UserService {

    fun save(userDto: UserDto)
    fun findUserByName(userName: String): Optional<User>
}
