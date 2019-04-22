package com.example.coroutines.guide.compose

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 挂起函数的组合
 * 惰性并发执行
 *
 * @author DQDana
 * @since 2019/4/15 12:56 PM
 */

fun main() = runBlocking {
  val time = measureTimeMillis {
    val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
    val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
    // 执行一些计算
    delay(500L)
    one.start() // one.await() : 两者都会触发 协程启动, 但是 await 并不是预期用例
    two.start()
    println("The answer is ${one.await() + two.await()}")
  }
  println("Completed in $time ms")
}