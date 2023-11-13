package de.hermes.technicalcasekotlin.entities

import jakarta.persistence.*
import java.time.Instant

@Entity

class Job(
        val timestamp: Instant,
        val commands: Int,
        val result: Int,
        val duration: Double
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}