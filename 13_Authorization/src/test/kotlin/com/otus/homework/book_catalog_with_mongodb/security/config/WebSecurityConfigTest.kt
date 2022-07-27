package com.otus.homework.book_catalog_with_mongodb.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.ContentType
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus
import com.otus.homework.book_catalog_with_mongodb.integration.IntegrationTestBased
import com.otus.homework.book_catalog_with_mongodb.security.dto.UserDto
import com.otus.homework.book_catalog_with_mongodb.security.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.servlet.function.ServerResponse.status

@AutoConfigureMockMvc
class WebSecurityConfigTest : IntegrationTestBased() {


    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var userService: UserService

    @Test
    fun `create page is not protected and user will created`() {

        val objectMapper = ObjectMapper()
        val username = "user"
        val password = "password"
        val userDto = objectMapper.writeValueAsString(UserDto(username, password))

        mvc
            .perform(
                post("/api/v1/users")
                    .contentType(ContentType.APPLICATION_JSON.mimeType)
                    .content(userDto)
            )
            .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
            .andExpect {
                status(HttpStatus.SC_OK)
            }

        assertThat(userService.findUserByName(username)).isPresent
    }

    @Test
    fun `login page is protected and user is authenticated after login`() {
        userService.save(UserDto("admin", "admin"))

        mvc
            .perform(
                SecurityMockMvcRequestBuilders
                    .formLogin("/login")
                    .user("admin")
                    .password("admin")
            )
            .andExpect {
                status(HttpStatus.SC_OK)
            }
            .andExpect(SecurityMockMvcResultMatchers.authenticated())

    }

    @Test
    fun `protected page redirects to login`() {
        mvc
            .get("/")
            .andExpect {
                status { is3xxRedirection() }
                redirectedUrlPattern("**/login")
            }
    }


    @Test
    fun `invalid user is not authenticated after login`() {
        this.mvc
            .perform(
                SecurityMockMvcRequestBuilders
                    .formLogin("/login")
                    .user("invalid")
                    .password("invalid")
            )
            .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection)
    }
}