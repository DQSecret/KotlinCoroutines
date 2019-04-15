package com.example.coroutines.guide.compose

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 挂起函数的组合
 * 默认顺序调用
 *
 * @author DQDana
 * @since 2019/4/15 11:48 AM
 */

fun main() = runBlocking {
  val time = measureTimeMillis {
    val one = doSomethingUsefulOne()
    var two = 0
    // 顺序执行, 可以根据状态值判断
    if (one == 13) {
      two = doSomethingUsefulTwo()
    } else {
      println("The one is error code is $one")
    }
    println("The answer is ${one + two}")
  }
  println("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
  delay(1_000L)
  return 15
}

suspend fun doSomethingUsefulTwo(): Int {
  delay(1_000L)
  return 30
}