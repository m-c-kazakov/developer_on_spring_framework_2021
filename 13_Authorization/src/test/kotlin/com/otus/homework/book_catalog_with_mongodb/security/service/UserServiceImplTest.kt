package com.otus.homework.book_catalog_with_mongodb.security.service

import com.otus.homework.book_catalog_with_mongodb.integration.IntegrationTestBased
import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.model.Role
import com.otus.homework.book_catalog_with_mongodb.security.model.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class UserServiceImplTest: IntegrationTestBased() {
    @Autowired
    lateinit var userService: UserService

    @Test
    fun save() {

        val userName = "USER"
        val password = "USER"
        userService.save(UserDto(userName, password))

//        assertThat(userService.findUserByName(userName))
//            .isNotNull
//            .usingRecursiveComparison()
//            .ignoringFields("id")
//            .isEqualTo(User(userName, password))

    }

    @Test
    fun findUserByName() {
    }
}