package com.example.coroutines.guide

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 全局协程 - 守护线程
 * @author DQDana
 * @since 2019/4/12 12:57 PM
 */

fun main() = runBlocking {

    // 以下代码在 GlobalScope 中启动了一个长期运行的协程，
    // 该协程每秒输出“I'm sleeping”两次，
    GlobalScope.launch {
        repeat(1_000) {
            println("I'm sleeping $it ...")
            delay(500L)
        }
    }

    // 在主函数中延迟一段时间后返回。
    delay(1300L)

    // 此时主线程的生命周期, 只有1.3s, 结束后, GlobalScope.launch{} 随之结束
}