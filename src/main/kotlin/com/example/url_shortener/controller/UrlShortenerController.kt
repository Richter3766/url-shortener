package com.example.url_shortener.controller

import com.example.url_shortener.model.dto.request.UrlRequestDto
import com.example.url_shortener.model.dto.response.SimpleMessageDto
import com.example.url_shortener.service.UrlShortenerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UrlShortenerController(
    private val urlShortenerService: UrlShortenerService
) {
    @PostMapping("")
    suspend fun shortenUrl(
        @RequestBody requestDto :UrlRequestDto
    ): SimpleMessageDto {
        val result = urlShortenerService.shortenUrl(requestDto.url)
        return SimpleMessageDto(result)
    }
}