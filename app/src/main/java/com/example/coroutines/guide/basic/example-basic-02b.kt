package com.example.coroutines.guide.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程测试代码
 * @author DQDana
 * @since 2019/4/11 3:01 PM
 */

fun main() = runBlocking<Unit> {
  GlobalScope.launch {
    delay(1_000L)
    println("World!")
  }
  println("Hello,")
  delay(2_000L)
}