package com.otus.homework.book_catalog_with_mongodb.security.service

import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.model.Role
import com.otus.homework.book_catalog_with_mongodb.security.model.User
import com.otus.homework.book_catalog_with_mongodb.security.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer
import javax.annotation.PostConstruct

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    @PostConstruct
    fun setUser() {
        userRepository.deleteAll()
        this.save(UserDto("admin", "admin"))

    }

    override fun save(userDto: UserDto) {

        userRepository
            .findUserByUserName(userDto.userName)
            .ifPresentOrElse(
                { throw RuntimeException("Пользователь с таким именем=${it.userName} уже существует") },
                {
                    val user = User(userDto.userName, passwordEncoder.encode(userDto.password))
                    userRepository.save(user)
                }
            )


    }

    override fun findUserByName(userName: String): Optional<User> {
        return userRepository
            .findUserByUserName(userName)
    }
}