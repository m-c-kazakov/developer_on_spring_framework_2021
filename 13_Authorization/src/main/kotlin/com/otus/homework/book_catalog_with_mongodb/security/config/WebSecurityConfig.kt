package com.otus.homework.book_catalog_with_mongodb.security.config

import com.otus.homework.book_catalog_with_mongodb.security.model.Role
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * Spring Security without the WebSecurityConfigurerAdapter:
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */
@EnableWebSecurity
open class WebSecurityConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedOrigins("http://192.168.1.4:3000/", "http://localhost:3000/")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true)
    }


    @Bean
    open fun httpSecurity(httpSecurity: HttpSecurity): SecurityFilterChain {

        httpSecurity
            .authorizeHttpRequests {
                it.antMatchers("/login").permitAll()
                it.antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                it.antMatchers(HttpMethod.GET, "/api/v1/users").hasAuthority(Role.ADMIN.name)
                it.antMatchers(HttpMethod.POST, "/api/v1/books").hasAnyAuthority(Role.USER.name, Role.ADMIN.name)
            }
            .formLogin {
                it.successHandler(AuthenticationSuccessHandler { request, response, authentication ->
                    run {

                    }
                })
//                    .usernameParameter("username")
//                    .passwordParameter("password")
            }
            .csrf()
            .disable()
            .cors()
            .and()

        return httpSecurity.build()
    }


    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}