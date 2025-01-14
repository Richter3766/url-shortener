package com.example.url_shortener.config

import com.example.url_shortener.utils.RedisUtil
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.ReactiveRedisTemplate

@Configuration
class RedisUtilConfig(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>
) {
    @PostConstruct
    fun initialize(){
        RedisUtil.init(reactiveRedisTemplate)
    }
}