package de.hermes.technicalcasekotlin.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CommonController {
    @GetMapping("/")
    fun redirect(): ResponseEntity<Void> =
        ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header("Location", "/swagger-ui/index.html").build()
}