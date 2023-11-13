package de.hermes.technicalcasekotlin

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName


@TestConfiguration(proxyBeanMethods = false)
internal class IntegrationTestBase {


	companion object{
		val postgresContainer = postgresContainer()

		@JvmStatic
		fun postgresContainer(): PostgreSQLContainer<*> {
			return PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
		}

		@BeforeAll
		@JvmStatic
		fun beforeAll() {
			postgresContainer.start()
		}

		@AfterAll
		@JvmStatic
		fun afterAll() {
			postgresContainer.stop()
		}

		@DynamicPropertySource
		@JvmStatic
		fun configureProperties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
			registry.add("spring.datasource.username", postgresContainer::getUsername)
			registry.add("spring.datasource.password", postgresContainer::getPassword)
		}
	}

}

fun main(args: Array<String>) {
	fromApplication<TechnicalCaseKotlinApplication>().with(IntegrationTestBase::class).run(*args)
}
