package de.hermes.technicalcasekotlin.controller

import de.hermes.technicalcasekotlin.entities.Job
import de.hermes.technicalcasekotlin.requests.EnterPathRequest
import de.hermes.technicalcasekotlin.service.RobotService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tibber-developer-test")
@Tag(name = "Robot Controller API")
class RobotController(val robotService: RobotService) {

    @Operation(summary = "Get 'Hallo Welt' as response.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Returns 'Hallo Welt'", content = [
                (Content(mediaType = "plain/text;charset=UTF-8", examples = [ExampleObject("Hallo Welt")]))]
            )]
    )
    @GetMapping("/hallo-welt")
    fun halloWelt(): String {
        return "Hallo Welt"
    }


    @Operation(summary = "Set the a path for the cleaning robot to follow")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Finished Job Result", content = [
                    (Content(mediaType = "application/json", schema = Schema(implementation = Job::class)))]
            )]
    )
    @PostMapping("/enter-path")
    fun enterPath(@RequestBody request: EnterPathRequest): Job = robotService.executeRequest(request)
}