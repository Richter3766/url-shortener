package com.example.url_shortener.service.util

object BaseConverter{
    private const val BASE62CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

    fun convertToBase62(targetId: Long): String {
        val sb = StringBuilder()
        var num = targetId

        while (num > 0) {
            sb.append(BASE62CHARSET[(num % 62).toInt()])
            num /= 62
        }

        return sb.reverse().toString()
    }

    fun convertFromBase62(shortUrl: String): Long {
        var number = 0L
        for (char in shortUrl) {
            number = number * 62 + BASE62CHARSET.indexOf(char)
        }
        return number
    }
}