package com.otus.homework.book_catalog_with_mongodb.security.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


/**
 * Spring Security without the WebSecurityConfigurerAdapter:
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */
@EnableWebSecurity
open class WebSecurityConfig {


    @Bean
    open fun httpSecurity(httpSecurity: HttpSecurity): SecurityFilterChain {

        httpSecurity
            .cors()
            .and()
            .authorizeHttpRequests {
                it
                    .antMatchers("/login")
                    .permitAll()
                it
                    .anyRequest()
                    .authenticated()
            }
            .formLogin {
                it.loginPage("/login")
            }

        return httpSecurity.build()
    }


    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}