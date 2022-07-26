package com.otus.homework.book_catalog_with_mongodb.security.controller

import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.service.UserService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
class SecurityController(
    private val userService: UserService
) {

    @PostMapping("/api/v1/users")
    fun createUser(@RequestBody userDto: UserDto) {
        userService.save(userDto)
    }
}