package com.otus.homework.genetic.algorithm.application.steps

import com.otus.homework.genetic.algorithm.application.dto.Animal

interface CrossingPopulation {

    fun execute(population: List<Animal>): List<Animal>
}
