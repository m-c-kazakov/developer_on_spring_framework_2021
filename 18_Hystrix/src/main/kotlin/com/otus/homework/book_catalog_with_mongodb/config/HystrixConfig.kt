package com.otus.homework.book_catalog_with_mongodb.config

import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.context.annotation.Configuration

@EnableHystrix
@Configuration
open class HystrixConfig {
}