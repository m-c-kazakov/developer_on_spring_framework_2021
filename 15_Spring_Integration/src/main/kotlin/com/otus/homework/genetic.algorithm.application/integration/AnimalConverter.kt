package com.otus.homework.genetic.algorithm.application.integration

import com.otus.homework.genetic.algorithm.application.dto.Animal
import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway

@MessagingGateway
interface AnimalConverter {

    @Gateway(requestChannel = "itemsChannel", replyChannel = "animalChannel")
    fun execute(population: List<Animal>) : List<Animal>
}