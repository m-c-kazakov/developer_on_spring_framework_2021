package com.otus.homework.book_catalog_with_mongodb.security.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class WebSecurityConfig {
// WebSecurityConfigurerAdapter
    @Bean
    fun webSecurityCustomizer() : WebSecurityCustomizer {
        return WebSecurityCustomizer {
            web -> web.i
        }
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}