package com.otus.homework.book_catalog_with_mongodb.actuator

import com.otus.homework.book_catalog_with_mongodb.service.BookService
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class BooksNotEmptyHealthCheck(private val bookService: BookService) : HealthIndicator {

    override fun health(): Health = when {
        bookService.findAll().isNotEmpty() -> Health
            .up()
            .withDetail("message", "OK")
            .build()
        else -> Health
            .down()
            .withDetail("message", "WARNING")
            .build()
    }
}