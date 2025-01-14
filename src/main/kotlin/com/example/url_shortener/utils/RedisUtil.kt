package com.example.url_shortener.utils

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
object RedisUtil {
    private lateinit var reactiveRedisTemplate: ReactiveRedisTemplate<String, String>

    fun init(template: ReactiveRedisTemplate<String, String>) {
        reactiveRedisTemplate = template
    }

    suspend fun setValue(key: String, value: String) {
        reactiveRedisTemplate.opsForValue()
            .set(key, value)
            .awaitSingle()
    }

    suspend fun setValue(key: String, value: String,  expiration: Duration) {
        reactiveRedisTemplate.opsForValue()
            .set(key, value, expiration)
            .awaitSingle()
    }

    suspend fun getValue(key: String): String? {
        return reactiveRedisTemplate.opsForValue()
            .get(key)
            .awaitSingleOrNull()
    }

    suspend fun deleteValue(key: String): Boolean {
        return reactiveRedisTemplate.opsForValue()
            .delete(key)
            .awaitSingle()
    }
}