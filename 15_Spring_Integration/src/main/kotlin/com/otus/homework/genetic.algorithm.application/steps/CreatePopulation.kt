package com.otus.homework.genetic.algorithm.application.steps

import com.otus.homework.genetic.algorithm.application.dto.Animal

interface CreatePopulation {

    fun execute(): List<Animal>
}
