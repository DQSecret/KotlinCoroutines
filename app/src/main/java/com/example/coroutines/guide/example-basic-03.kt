package com.example.coroutines.guide

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程测试代码
 * @author DQDana
 * @since 2019/4/11 3:08 PM
 */

fun main() = runBlocking {

    val job = GlobalScope.launch {
        delay(1_000L)
        println("World!")
    }
    println("Hello,")
    job.join()
}