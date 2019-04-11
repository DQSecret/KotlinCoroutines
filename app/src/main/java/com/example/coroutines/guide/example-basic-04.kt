package com.example.coroutines.guide

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程测试代码
 * @author DQDana
 * @since 2019/4/11 3:14 PM
 */

fun main() = runBlocking {

    launch {
        // 4
        delay(2_000L)
        println("Task from runBlocking")
    }

    coroutineScope {
        // 2 Creates a new coroutine scope (协程范围)
        launch {
            delay(1000L)
            println("Task from nested(嵌套) launch")
        }

        // 1
        delay(500L)
        println("Task from coroutine scope")
    }

    // 3
    println("Coroutine scope is over")
}