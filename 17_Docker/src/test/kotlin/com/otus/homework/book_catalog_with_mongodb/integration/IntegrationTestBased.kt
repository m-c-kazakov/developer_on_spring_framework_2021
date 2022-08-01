package com.otus.homework.book_catalog_with_mongodb.integration

import com.otus.homework.book_catalog_with_mongodb.integration.annotation.IT
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer


@IT
abstract class IntegrationTestBased{


    companion object {

        private val container: MongoDBContainer = MongoDBContainer("mongo:4.0.10")
        private val log: Logger = LoggerFactory.getLogger(IntegrationTestBased::class.java)

        @BeforeAll
        @JvmStatic
        internal fun runContainer() {
            log.info("Запуск тестового контейнере")
            container.start()
        }

        @DynamicPropertySource
        @JvmStatic
        internal fun properties(registry: DynamicPropertyRegistry) {
            mutableMapOf<String, String>("asdf" to "afd")
            log.info("Установка свойств для тестового контейнера")
            registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl)
        }

        @AfterAll
        @JvmStatic
        internal fun stopContainer() {
            log.info("Остановка тестового контейнере")
            container.stop()
        }

    }
}