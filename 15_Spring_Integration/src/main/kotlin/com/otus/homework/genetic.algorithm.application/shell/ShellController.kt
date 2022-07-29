package com.otus.homework.genetic.algorithm.application.shell

import com.otus.homework.genetic.algorithm.application.service.GeneticAlgorithm
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class ShellController(val geneticAlgorithm: GeneticAlgorithm) {

    @ShellMethod(key = ["start", "st"], value = "start GeneticAlgorithm")
    fun start() {
        geneticAlgorithm.execute()
    }
}