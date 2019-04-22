package com.example.coroutines.guide.context

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

/**
 * 上下文中的任务
 * @author DQDana
 * @since 2019-04-22 18:21
 */

fun main() = runBlocking {
    println("My job is ${coroutineContext[Job]}")
}