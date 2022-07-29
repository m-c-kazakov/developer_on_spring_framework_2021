package com.otus.homework.genetic.algorithm.application.descriptionPopulation

import java.util.concurrent.ThreadLocalRandom

enum class TypeOfPowerSupply {
    HERBIVORE,
    OMNIVOROUS,
    CARNIVOROUS;

    companion object {

        fun random(): TypeOfPowerSupply {
            val randomIndex = ThreadLocalRandom
                .current()
                .nextInt(0, values().size)
            return values()[randomIndex]
        }

    }


}