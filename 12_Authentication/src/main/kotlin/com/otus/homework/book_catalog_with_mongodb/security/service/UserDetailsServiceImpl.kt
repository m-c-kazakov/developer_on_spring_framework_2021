package com.otus.homework.book_catalog_with_mongodb.security.service

import com.otus.homework.book_catalog_with_mongodb.security.model.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userService: UserService) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        requireNotNull(username) { "Username cannot be null" }
        val user = userService.findUserByName(username)

        return UserDetailsImpl(user)
    }
}