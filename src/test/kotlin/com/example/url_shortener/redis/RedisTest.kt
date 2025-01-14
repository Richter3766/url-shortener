package com.example.url_shortener.redis

import com.example.url_shortener.utils.RedisUtil
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.ReactiveRedisTemplate

@SpringBootTest
class RedisTest(
    val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>
): BehaviorSpec({
    beforeTest {
        RedisUtil.init(reactiveRedisTemplate)
    }

    afterTest {
        RedisUtil.deleteValue("key")
    }

    Context("redis 연결 테스트"){
        Given("redis get 요청을 보내면"){

            When("키가 있을 경우"){
                RedisUtil.setValue("key", "value")

                Then("값을 제대로 가져온다."){
                    val result = RedisUtil.getValue("key")
                    result shouldBe "value"
                }
            }
        }
    }
})