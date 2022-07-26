package com.otus.homework.book_catalog_with_mongodb.security.service

import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.model.Role
import com.otus.homework.book_catalog_with_mongodb.security.model.User
import com.otus.homework.book_catalog_with_mongodb.security.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun save(userDto: UserDto) {

        userRepository
            .findUserByUserName(userDto.userName)
            .ifPresentOrElse(
                { throw RuntimeException("Пользователь с таким именем=${it.userName} уже существует") },
                {
                    val user = User(userDto.userName, userDto.password, mutableListOf(Role.USER))
                    userRepository.save(user)
                }
            )


    }

    override fun findUserByName(userName: String): User {
        return userRepository
            .findUserByUserName(userName)
            .orElseThrow()
    }
}