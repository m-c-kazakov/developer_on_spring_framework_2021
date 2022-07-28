package com.otus.homework.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class BookCatalogApplication

fun main(args: Array<String>) {
    runApplication<BookCatalogApplication>(*args)
}
