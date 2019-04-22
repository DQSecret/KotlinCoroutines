package com.example.coroutines.guide.compose

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Cancellation - 协程取消 始终会被传递的, 通过层次结构
 * @author DQDana
 * @since 2019-04-22 14:30
 */

fun main() = runBlocking<Unit> {
  try {
    failedConcurrentSum()
  } catch (e: ArithmeticException) {
    println("Computation failed with ArithmeticException")
  }
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
  val one = async {
    try {
      delay(Long.MIN_VALUE) // 模拟一个长时间的运算
      42
    } finally {
      println("First child was cancelled")
    }
  }
  val two = async<Int> {
    println("Second child throws an exception")
    throw ArithmeticException()
  }
  one.await() + two.await()
}