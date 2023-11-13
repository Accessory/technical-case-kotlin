package de.hermes.technicalcasekotlin

import de.hermes.technicalcasekotlin.entities.Job
import de.hermes.technicalcasekotlin.enums.Direction
import de.hermes.technicalcasekotlin.models.Command
import de.hermes.technicalcasekotlin.models.Position
import de.hermes.technicalcasekotlin.repositories.JobRepository
import de.hermes.technicalcasekotlin.requests.EnterPathRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatusCode
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class IntegrationTest(@Autowired val jobRepository: JobRepository,
                               @Autowired val restTemplate: TestRestTemplate,
                               @LocalServerPort val port:Int) :
    IntegrationTestBase() {

    @Test
    fun testRepository() {
        val jobDb = jobRepository.save(Job(Instant.now(), 2, 4, 0.123))
        assertEquals(jobDb.result, 4)
        assertEquals(jobDb.commands, 2)
        assertNotEquals(jobDb.id, 0)
    }

    @Test
    fun getHalloWelt() {
        val result = restTemplate.getForObject("http://localhost:$port/tibber-developer-test/hallo-welt", String::class.java)
        assertEquals(result, "Hallo Welt")
    }

    @Test
    fun postEnterPath() {
        val epr = EnterPathRequest(Position(0,0), listOf(Command("north", 2), Command("east", 2)))
        val request = HttpEntity(epr, HttpHeaders())
        val result = restTemplate.postForEntity<Job>("http://localhost:$port/tibber-developer-test/enter-path", request)
        assertEquals(result.statusCode.value(),200)
        assertEquals(result.body?.result, 5)
        assertEquals(result.body?.commands, 2)
        assertNotEquals(result.body?.id, 0)
    }
}