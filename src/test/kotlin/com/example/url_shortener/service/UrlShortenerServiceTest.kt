package com.example.url_shortener.service

import com.example.url_shortener.utils.RedisUtil
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveMaxLength
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.redis.core.ReactiveRedisTemplate
import reactor.core.publisher.Mono


class UrlShortenerServiceTest: BehaviorSpec({
    val shortenerService = UrlShortenerService()
    val reactiveRedisTemplate = mockk<ReactiveRedisTemplate<String, String>>()
    RedisUtil.init(reactiveRedisTemplate)

    Context("shortenUrl 테스트"){
        Given("Redis에 url이 있을 때"){
            val targetUrl = "test Url"
            val shortenUrl = "short Url"

            When("해당 값을 Redis에서 가져오고"){
                every { reactiveRedisTemplate.opsForValue().get(targetUrl) } returns Mono.just(shortenUrl)

                Then("값을 반환한다"){
                    val result = shortenerService.shortenUrl(targetUrl)
                    result shouldBe shortenUrl
                }
            }
        }

        Given("Redis에 url이 없을 때"){
            val targetUrl = "test Url"

            When("Url을 생성하고 Redis에 저장한 후"){
                every { reactiveRedisTemplate.opsForValue().get(targetUrl) } returns Mono.empty()
                coEvery { reactiveRedisTemplate.opsForValue().set(any(), any()) } returns Mono.just(true)

                Then("값을 반환한다"){
                    val result = shortenerService.shortenUrl(targetUrl)
                    result shouldHaveMaxLength 11
                }
            }
        }
    }
}) 