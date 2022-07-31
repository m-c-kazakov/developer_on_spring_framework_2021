package com.otus.homework.genetic.algorithm.application.integration

import com.otus.homework.genetic.algorithm.application.steps.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.Pollers
import org.springframework.integration.scheduling.PollerMetadata

@Configuration
open class IntegrationConfig {

    private val QUEUE_CAPACITY = 10
    private val ANIMAL_CONVERTER_METHOD_NAME = "execute"

    @Bean
    open fun itemsChannel(): QueueChannel? {
        return MessageChannels
            .queue(QUEUE_CAPACITY)
            .get()
    }

    @Bean
    open fun animalChannel(): PublishSubscribeChannel? {
        return MessageChannels
            .publishSubscribe()
            .get()
    }

    @Bean(name = [PollerMetadata.DEFAULT_POLLER])
    open fun poller(): PollerMetadata? {
        return Pollers
            .fixedRate(100)
            .maxMessagesPerPoll(2)
            .get()
    }

    @Bean
    open fun geneticAlgorithmFlow(
        crossingPopulation: CrossingPopulation,
        mutationPopulation: MutationPopulation
    ): IntegrationFlow? {
        return IntegrationFlows
            .from(itemsChannel())
            .handle(crossingPopulation)
            .handle(mutationPopulation)
            .channel(animalChannel())
            .get()
    }

}