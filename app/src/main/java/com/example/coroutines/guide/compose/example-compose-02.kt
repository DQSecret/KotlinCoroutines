package com.example.coroutines.guide.compose

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 挂起函数的组合
 * 并发执行
 *
 * @author DQDana
 * @since 2019/4/15 12:51 PM
 */

fun main() = runBlocking {
  val time = measureTimeMillis {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    println("The answer is ${one.await() + two.await()}")
  }
  println("Completed in $time ms")
}