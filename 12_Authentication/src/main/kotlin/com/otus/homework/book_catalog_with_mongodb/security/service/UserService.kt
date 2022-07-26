package com.otus.homework.book_catalog_with_mongodb.security.service

import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.model.User

interface UserService {

    fun save(userDto: UserDto)
    fun findUserByName(userName: String): User
}
