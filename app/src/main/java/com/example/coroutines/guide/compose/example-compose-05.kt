package com.example.coroutines.guide.compose

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 使用 async  进行结构化并发
 * @author DQDana
 * @since 2019/4/17 8:01 PM
 */

fun main() = runBlocking {
  val time = measureTimeMillis {
    println("The answer is ${concurrentSum()}")
  }
  println("Completed in $time ms")
}

suspend fun concurrentSum(): Int = coroutineScope {
  val one = async { doSomethingUsefulOne() }
  val two = async { doSomethingUsefulTwo() }
  one.await() + two.await()
}