package com.otus.homework.batch

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.io.FileNotFoundException


@Service
class SqlExecutioner(
    private val jdbc: JdbcTemplate,
) {

    @Suppress("unused")
    fun execute(sqlResourceFilename: String) {
        jdbc.execute(sqlResourceFilename.resourceAsText())
    }
}

fun String.resourceAsText(): String =
    Thread.currentThread().contextClassLoader
        .getResourceAsStream(this)
        ?.bufferedReader()
        ?.readText()
        ?: throw FileNotFoundException("Failed to get resource at '$this'")

