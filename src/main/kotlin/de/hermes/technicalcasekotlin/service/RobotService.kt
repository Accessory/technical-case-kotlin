package de.hermes.technicalcasekotlin.service

import de.hermes.technicalcasekotlin.entities.Job
import de.hermes.technicalcasekotlin.enums.Direction
import de.hermes.technicalcasekotlin.enums.convertToDirection
import de.hermes.technicalcasekotlin.repositories.JobRepository
import de.hermes.technicalcasekotlin.requests.EnterPathRequest
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.system.measureNanoTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Service
class RobotService(val jobRepository: JobRepository) {
    fun executeRequest(req: EnterPathRequest): Job = jobRepository.save(createJobFromEnterPathRequest(req));

    fun createJobFromEnterPathRequest(req: EnterPathRequest): Job {
        val visited = mutableSetOf(req.start.copy())
        val elapsed  = measureNanoTime {
            val start = req.start
            for (command in req.commands) {
                repeat(command.steps) {
                    var direction = convertToDirection(command.direction)
                    when (direction) {
                        Direction.North -> --start.y
                        Direction.South -> ++start.y
                        Direction.West -> --start.x
                        Direction.East -> ++start.x
                        null -> {}
                    }
                    visited.add(start.copy())
                }
            }
        }
        val duration = elapsed.toDuration(DurationUnit.NANOSECONDS).toDouble(DurationUnit.SECONDS)
        return Job(Instant.now(), req.commands.count(), visited.count(), duration);
    }
}