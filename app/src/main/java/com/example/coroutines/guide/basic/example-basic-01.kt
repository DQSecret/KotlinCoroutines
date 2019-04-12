package com.example.coroutines.guide.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 协程测试代码
 * @author DQDana
 * @since 2019/4/11 2:59 PM
 */

fun main() {
  GlobalScope.launch {
    delay(1_000L)
    println("World!")
  }
  println("Hello,")
  Thread.sleep(2_000L)
}