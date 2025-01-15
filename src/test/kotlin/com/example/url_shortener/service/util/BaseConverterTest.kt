package com.example.url_shortener.service.util

import com.example.url_shortener.service.util.BaseConverter.convertFromBase62
import com.example.url_shortener.service.util.BaseConverter.convertToBase62
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


class BaseConverterTest: StringSpec({
    "'주어진 ID <-> 단축 URL' 확인 테스트" {
        val targetId = 121520000001L
        val shortUrl = convertToBase62(targetId)
        convertFromBase62(shortUrl) shouldBe targetId
    }
})