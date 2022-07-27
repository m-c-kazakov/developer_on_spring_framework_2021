package com.otus.homework.book_catalog_with_mongodb.security.service

import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.model.Role
import com.otus.homework.book_catalog_with_mongodb.security.model.User
import com.otus.homework.book_catalog_with_mongodb.security.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun save(userDto: UserDto) {

        val optionalUser = userRepository.findUserByUserName(userDto.username)

        if (optionalUser.isEmpty) {
            val user = User(userDto.username, passwordEncoder.encode(userDto.password))
            userRepository.save(user)
        } else {
            throw RuntimeException("Пользователь с таким именем=${userDto.username} уже существует")
        }
    }

    override fun findUserByName(userName: String): Optional<User> {
        return userRepository
            .findUserByUserName(userName)
    }

    override fun findAll(): List<UserDto> {
        return userRepository.findAll().map{
            UserDto(it.userName, it.password)
        }
    }

    override fun saveAdminUser(userDto: UserDto) {
        val optionalUser = userRepository.findUserByUserName(userDto.username)

        if (optionalUser.isEmpty) {
            val user = User(userDto.username, passwordEncoder.encode(userDto.password), mutableListOf(Role.ADMIN))
            userRepository.save(user)
        } else {
            throw RuntimeException("Админ с таким именем=${userDto.username} уже существует")
        }
    }
}