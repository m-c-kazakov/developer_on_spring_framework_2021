package com.otus.homework.genetic.algorithm.application.dto

import com.otus.homework.genetic.algorithm.application.descriptionPopulation.TypeOfMovement
import com.otus.homework.genetic.algorithm.application.descriptionPopulation.TypeOfPowerSupply

data class Animal(
    var typeOfPowerSupply : TypeOfPowerSupply,
    val typeOfMovement: TypeOfMovement
) {

}