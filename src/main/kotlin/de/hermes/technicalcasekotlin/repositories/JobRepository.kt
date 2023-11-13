package de.hermes.technicalcasekotlin.repositories

import de.hermes.technicalcasekotlin.entities.Job
import org.springframework.data.jpa.repository.JpaRepository

interface JobRepository : JpaRepository<Job, Long>