package de.hermes.technicalcasekotlin.controller

import de.hermes.technicalcasekotlin.entities.Job
import de.hermes.technicalcasekotlin.requests.EnterPathRequest
import de.hermes.technicalcasekotlin.service.RobotService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tibber-developer-test")
class RobotController(val robotService: RobotService) {

    @GetMapping("/hallo-welt")
    fun halloWelt(): String {
        return "Hallo Welt"
    }

    @PostMapping("/enter-path")
    fun enterPath(@RequestBody request: EnterPathRequest): Job = robotService.executeRequest(request)
}