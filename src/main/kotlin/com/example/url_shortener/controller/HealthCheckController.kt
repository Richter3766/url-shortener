package com.example.url_shortener.controller

import com.example.url_shortener.model.dto.response.SimpleMessageDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health-check")
class HealthCheckController {
    @GetMapping("")
    suspend fun healthCheck(): ResponseEntity<SimpleMessageDto> {
        return ResponseEntity.ok(SimpleMessageDto("health check"))
    }
}