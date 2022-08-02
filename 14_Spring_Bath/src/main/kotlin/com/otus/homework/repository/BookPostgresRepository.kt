package com.otus.homework.repository

import com.otus.homework.model.postgres.BookPostgres
import org.springframework.data.repository.CrudRepository

interface BookPostgresRepository : CrudRepository<BookPostgres, Long> {
}