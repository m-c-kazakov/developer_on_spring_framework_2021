package com.otus.homework.genetic.algorithm.application.steps

import com.otus.homework.genetic.algorithm.application.descriptionPopulation.TypeOfMovement
import com.otus.homework.genetic.algorithm.application.descriptionPopulation.TypeOfPowerSupply
import com.otus.homework.genetic.algorithm.application.dto.Animal
import org.springframework.stereotype.Service


@Service
class CreatePopulationImpl : CreatePopulation {


    override fun execute(): List<Animal> {
        println("CreatePopulation")
        return (0..10)
            .map {
                Animal(TypeOfPowerSupply.random(), TypeOfMovement.random())
            }
            .toList()
    }

}