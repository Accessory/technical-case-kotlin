package de.hermes.technicalcasekotlin.service

import de.hermes.technicalcasekotlin.entities.Job
import de.hermes.technicalcasekotlin.models.Line
import de.hermes.technicalcasekotlin.repositories.JobRepository
import de.hermes.technicalcasekotlin.requests.EnterPathRequest
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.system.measureNanoTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Service
class RobotService(val jobRepository: JobRepository) {
    fun executeRequest(req: EnterPathRequest): Job = jobRepository.save(createJobFromEnterPathRequest(req))

    fun createJobFromEnterPathRequest(request: EnterPathRequest): Job {
        var sum = 1
        var intersections = 0
        val elapsed  = measureNanoTime {
            val currentPosition = request.start
            val lines = mutableListOf<Line>()
            val toCheck = request.commands.size - 1

            for (i in 0 until request.commands.size) {
                val command = request.commands[i]
                sum += command.steps
                when (command.direction.lowercase()) {
                    "north" -> {
                        lines.add(
                            Line(
                                currentPosition.copy(),
                                currentPosition.copy(y = currentPosition.y + (command.steps - if (toCheck == i) 0 else 1))
                            )
                        )
                        currentPosition.y += command.steps
                    }

                    "south" -> {
                        lines.add(
                            Line(
                                currentPosition.copy(),
                                currentPosition.copy(y = currentPosition.y - (command.steps - if (toCheck == i) 0 else 1))
                            )
                        )
                        currentPosition.y -= command.steps
                    }

                    "west" -> {
                        lines.add(
                            Line(
                                currentPosition.copy(),
                                currentPosition.copy(x = currentPosition.x - (command.steps - if (toCheck == i) 0 else 1))
                            )
                        )
                        currentPosition.x -= command.steps
                    }

                    "east" -> {
                        lines.add(
                            Line(
                                currentPosition.copy(),
                                currentPosition.copy(x = currentPosition.x + (command.steps - if (toCheck == i) 0 else 1))
                            )
                        )
                        currentPosition.x += command.steps
                    }
                }
            }

            for (i in 0 until lines.size) {
                val l1 = lines[i]
                for (j in i + 1 until lines.size) {
                    val l2 = lines[j]
                    intersections += l1.intersections(l2)
                }
            }
        }

        val duration = elapsed.toDuration(DurationUnit.NANOSECONDS).toDouble(DurationUnit.SECONDS)
        return Job(
            timestamp = Instant.now(),
            result = sum - intersections,
            duration =duration,
            commands = request.commands.size
        )
    }
}