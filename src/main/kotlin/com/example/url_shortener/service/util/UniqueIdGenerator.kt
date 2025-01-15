package com.example.url_shortener.service.util

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.LocalDateTime
import java.time.ZoneOffset

class SnowflakeGenerator(private val dataCenterId: Long, private val serverId: Long) {
    private val specificDateTime = LocalDateTime.of(2025, 1, 1, 0, 0, 0)
    private val epochTime = specificDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()

    private var lastTimestamp: Long = -1
    private var sequence: Long = 0
    private val mutex = Mutex()

    suspend fun generateId(): Long {
        val timestamp = System.currentTimeMillis()

        return mutex.withLock {
            if (timestamp == lastTimestamp) {
                sequence = (sequence + 1) and 0b111111111111
                val timeout = System.currentTimeMillis() + 100
                while (System.currentTimeMillis() <= lastTimestamp) {
                    if (System.currentTimeMillis() > timeout) {
                        return@withLock generateId()
                    }
                }
            } else {
                sequence = 0
            }

            lastTimestamp = timestamp
            var id = 0L
            id = id or ((timestamp - epochTime) shl 22)
            id = id or (dataCenterId shl 17)
            id = id or (serverId shl 12)
            id = id or sequence

            id
        }
    }
}

object UniqueIdGenerator {
    private val generator = SnowflakeGenerator(dataCenterId = 1, serverId = 1)

    suspend fun generateId(): Long {
        return generator.generateId()
    }
}
