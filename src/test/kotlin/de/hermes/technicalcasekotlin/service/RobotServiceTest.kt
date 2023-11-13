package de.hermes.technicalcasekotlin.service

import de.hermes.technicalcasekotlin.entities.Job
import de.hermes.technicalcasekotlin.models.Command
import de.hermes.technicalcasekotlin.models.Position
import de.hermes.technicalcasekotlin.repositories.JobRepository
import de.hermes.technicalcasekotlin.requests.EnterPathRequest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.Instant

class RobotServiceTest {

    private var jobRepositoryMock = mockk<JobRepository>()
    private var sut: RobotService = RobotService(jobRepositoryMock)

    @Test
    fun executeRequest() {
        val epr = EnterPathRequest(Position(0,0), listOf(Command("north", 2), Command("east", 2)))
        val job = Job(Instant.now(), 2, 5, 0.0001)
        every { jobRepositoryMock.save(any()) }.returns(job)
        val result = sut.executeRequest(epr)
        assertEquals(job.id, result.id)
        assertEquals(job.commands, result.commands)
        assertEquals(job.result, result.result)
    }

    @Test
    fun createJobFromEnterPathRequest() {
        val epr = EnterPathRequest(Position(0,0), listOf(Command("north", 2), Command("east", 2)))
        val job = Job(Instant.now(), 2, 5, 0.0001)
        val result = sut.createJobFromEnterPathRequest(epr)
        assertEquals(job.id, result.id)
        assertEquals(job.commands, result.commands)
        assertEquals(job.result, result.result)
    }
}