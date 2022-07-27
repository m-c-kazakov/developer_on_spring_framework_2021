package com.otus.homework.book_catalog_with_mongodb.security.controller

import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/api/v1/users")
    fun getUsers(): List<UserDto> {
        return userService.findAll()
    }
}