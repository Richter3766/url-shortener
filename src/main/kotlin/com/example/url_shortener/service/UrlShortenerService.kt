package com.example.url_shortener.service

import com.example.url_shortener.utils.RedisUtil
import org.springframework.stereotype.Service

@Service
class UrlShortenerService {
    suspend fun shortenUrl(
        url: String
    ): String {
        val existedUrl = RedisUtil.getValue(url)
        if (existedUrl != null){
            return existedUrl
        }
        // 없는 경우
        // 1. 유일 ID 생성

        // 2. 단축 URL 생성

        // 3. URL Redis 에 저장한 후 반환

        return ""
    }
    
    fun redirectUrl(
        url: String,
    ){
        // 적절한 url인지 검사
        
        // 적절하지 않으면 에러 반환
        
        // 적절하면 redirect 해주기
    }
}