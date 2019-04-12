package com.example.coroutines.guide

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程轻量测试
 * @author DQDana
 * @since 2019/4/12 12:51 PM
 */

fun main() = runBlocking {
  // 它启动了 10 万个协程，并且在一秒钟后，每个协程都输出一个点。
  // 现在，尝试使用线程来实现。会发生什么？
  // （很可能你的代码会产生某种内存不足的错误）
  repeat(100_000) {
    launch {
      delay(1_000L)
      println(".")
    }
  }
}