package com.otus.homework.genetic.algorithm.application.descriptionPopulation

import java.util.concurrent.ThreadLocalRandom

enum class TypeOfMovement {
    SWIMMING,
    FLIGHT,
    RUNNING;


    companion object {
        fun random(): TypeOfMovement {
            val randomIndex = ThreadLocalRandom
                .current()
                .nextInt(0, values().size)
            return values()[randomIndex]
        }
    }

}