package com.otus.homework.genetic.algorithm.application.service

import com.otus.homework.genetic.algorithm.application.integration.AnimalConverter
import com.otus.homework.genetic.algorithm.application.steps.CreatePopulation
import org.springframework.stereotype.Service

@Service
class GeneticAlgorithmImpl(
    private val animalConverter: AnimalConverter,
    private val createPopulation: CreatePopulation
) : GeneticAlgorithm {

    override fun execute() {
        println("Start GeneticAlgorithm")
        val startPopulation = createPopulation.execute()

        val endPopulation = animalConverter.execute(startPopulation)

        endPopulation
            .distinct()
            .forEach {
                println(it)
            }

    }
}