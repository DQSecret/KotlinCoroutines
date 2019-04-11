package com.example.coroutines.guide

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程测试代码
 * @author DQDana
 * @since 2019/4/11 4:21 PM
 */

fun main() = runBlocking {
    launchDoWorld()
    println("Hello,")
}

/**
 * suspend 即暂停的意思, 协程特性必需品
 */
suspend fun launchDoWorld() = coroutineScope {
    launch {
        println("World!")
    }
}