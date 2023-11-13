package de.hermes.technicalcasekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class TechnicalCaseKotlinApplication

fun main(args: Array<String>) {
	runApplication<TechnicalCaseKotlinApplication>(*args)
}