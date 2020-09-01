package com.vladan.color_interview.utils

import java.util.*

/**
 * Created by vladan on 8/27/2020
 */
object UuidGenerator {
    fun generateUuid(): String {
        return UUID.randomUUID().toString()
    }
}