package com.otus.homework.genetic.algorithm.application.steps

import com.otus.homework.genetic.algorithm.application.dto.Animal

interface MutationPopulation {

    fun execute(population: List<Animal>): List<Animal>
}
