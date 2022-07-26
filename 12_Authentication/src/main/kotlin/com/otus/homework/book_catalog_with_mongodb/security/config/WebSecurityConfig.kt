package com.otus.homework.book_catalog_with_mongodb.security.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

class WebSecurityConfig : WebSecurityConfigurerAdapter() {
// WebSecurityConfigurerAdapter
//    @Bean
//    fun webSecurityCustomizer() : WebSecurityCustomizer {
//        return WebSecurityCustomizer {
//            web -> web.i
//        }
//    }


    @Bean
    fun httpSecurity(httpSecurity: HttpSecurity): SecurityFilterChain {

        httpSecurity.authorizeHttpRequests {
            it.antMatchers("/login").permitAll()
            it.anyRequest().authenticated()
        }
//
//        httpSecurity.formLogin {
//            it.loginPage("/login")
//        }

        return httpSecurity.build()
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}