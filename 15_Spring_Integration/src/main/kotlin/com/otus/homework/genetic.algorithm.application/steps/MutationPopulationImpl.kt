package com.otus.homework.genetic.algorithm.application.steps

import com.otus.homework.genetic.algorithm.application.descriptionPopulation.TypeOfMovement
import com.otus.homework.genetic.algorithm.application.descriptionPopulation.TypeOfPowerSupply
import com.otus.homework.genetic.algorithm.application.dto.Animal
import org.springframework.stereotype.Service

@Service
class MutationPopulationImpl : MutationPopulation {


    override fun execute(population: List<Animal>): List<Animal> {
        println("START MutationPopulation")
        return population
            .flatMap {
                listOf(
                    it,
                    it.copy(typeOfPowerSupply = TypeOfPowerSupply.random()),
                    it.copy(typeOfMovement = TypeOfMovement.random())
                )
            }
            .toList()
    }
}