package com.example.coroutines.guide

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程测试代码
 * @author DQDana
 * @since 2019/4/11 4:18 PM
 */

fun main() = runBlocking {
    launch { doWorld() }
    println("Hello,")
}

/**
 * suspend 即暂停的意思, 协程特性必需品
 */
suspend fun doWorld() {
    delay(1_000L)
    println("World!")
}