package com.example.url_shortener.service

import com.example.url_shortener.service.util.BaseConverter.convertToBase62
import com.example.url_shortener.service.util.UniqueIdGenerator.generateId
import com.example.url_shortener.utils.RedisUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        val id = generateId()
        val shortUrl = convertToBase62(id)
        CoroutineScope(Dispatchers.IO).launch{
            RedisUtil.setValue(url, shortUrl)
            RedisUtil.setValue(shortUrl, url)
        }

        return shortUrl
    }
    
    fun redirectUrl(
        url: String,
    ){
        // 적절한 url인지 검사
        
        // 적절하지 않으면 에러 반환
        
        // 적절하면 redirect 해주기
    }
}