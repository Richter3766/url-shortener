package com.example.url_shortener.service.util

import com.example.url_shortener.service.util.UniqueIdGenerator.generateId
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UniqueIdGeneratorTest : BehaviorSpec({
    Context("일련번호 범위를 초과하지 않는 상황의 경우"){
        val ids = mutableSetOf<Long>()

        When("4096개 이내의 일련번호 동시 생성 시"){
            coroutineScope {
                val jobs = List(4096) {
                    launch {
                        val id = generateId()
                        ids.add(id)
                    }
                }
                jobs.forEach { it.join() }
            }

            Then("4096개의 각기 다른 ID가 생성되어야 한다."){
                ids.size shouldBe 4096
            }
        }


    }

    Context("일련번호 범위를 초과하는 상황일 경우"){

        When("4096개를 넘는 일련번호를 동시 생성 시"){
            repeat(4097) {
                generateId()
            }
            val lastTimestamp = System.currentTimeMillis()
            generateId()
            val newTimestamp = System.currentTimeMillis()

            Then("마지막으로 생성한 ID의 시간은 이전의 것과 달라야 한다.") {
                newTimestamp shouldNotBe lastTimestamp
            }
        }
    }
})