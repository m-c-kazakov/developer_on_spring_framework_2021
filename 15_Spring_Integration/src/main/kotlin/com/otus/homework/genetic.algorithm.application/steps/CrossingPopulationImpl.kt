package com.otus.homework.genetic.algorithm.application.steps

import com.otus.homework.genetic.algorithm.application.dto.Animal
import org.springframework.stereotype.Service

@Service
class CrossingPopulationImpl : CrossingPopulation {

    override fun execute(population: List<Animal>): List<Animal> {
        println("START CrossingPopulation")
        return (population.indices)
            .flatMap { i ->
                (1 until population.size).map { j ->
                    Pair(population[i], population[j])
                }
            }
            .filter {
                it.first != it.second
            }
            .flatMap {
                val firstParent = it.first
                val secondParent = it.first
                val firstChild = firstParent.copy(typeOfPowerSupply = secondParent.typeOfPowerSupply)
                val secondChild = secondParent.copy(typeOfPowerSupply = firstChild.typeOfPowerSupply)
                val thirdChild = secondParent.copy(typeOfMovement = secondChild.typeOfMovement)
                val fourthChild = secondParent.copy(typeOfMovement = firstChild.typeOfMovement)

                listOf(firstParent, secondParent, firstChild, secondChild, thirdChild, fourthChild)
            }
            .distinct()
    }
}