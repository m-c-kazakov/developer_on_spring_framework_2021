package com.otus.homework.controller

import com.otus.homework.repository.BookMongoRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@CrossOrigin
@RestController
class BookController(val repository: BookMongoRepository) {


    @GetMapping("/api/v1/books")
    fun findAll(
    ) {
        print("adsf")
    }
}
