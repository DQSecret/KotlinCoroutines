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

    // 延迟一段时间来等待另一个协程运行并不是一个好的选择.
    // 让我们显式（以非阻塞方式）等待所启动的后台 Job 执行结束:
    val job = GlobalScope.launch {
        delay(1_000L)
        println("World!")
    }
    println("Hello,")
    job.join()
}