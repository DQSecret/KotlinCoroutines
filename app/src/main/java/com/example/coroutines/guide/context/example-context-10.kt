package com.example.coroutines.guide.context

import kotlinx.coroutines.*

/**
 * Coroutine scope - 协同范围
 * @author DQDana
 * @since 2019-04-22 19:11
 */

class TestAct {

    private val mainScope = MainScope()

    fun onDestroy() {
        mainScope.cancel()
    }
}

class TestAct2 : CoroutineScope by CoroutineScope(Dispatchers.Default) {

    fun doSomething() {
        // 在示例中启动了 10 个协程，且每个都工作了不同的时长
        repeat(10) { i ->
            launch {
                delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒等等不同的时间
                println("Coroutine $i is done")
            }
        }
    }

    fun destroy() {
        cancel() // Extension on CoroutineScope
    }
}

fun main() = runBlocking {
    val activity = TestAct2()
    activity.doSomething() // 运行测试函数
    println("Launched coroutines")
    delay(500L) // 延迟半秒钟
    println("Destroying activity!")
    activity.destroy() // 取消所有的协程
    delay(1000) // 为了在视觉上确认它们没有工作
}