package com.example.coroutines.guide.cancel

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * 超时
 * @author DQDana
 * @since 2019/4/12 6:16 PM
 */

fun main() = runBlocking {

  val result = withTimeoutOrNull(1_300L) {
    repeat(1000) {
      println("I'm sleeping $it ...")
      delay(500L)
    }
    // 在它运行得到结果之前取消它, 会返回以下数据, 默认为 Null
    "Done"
  }

  println("Result is $result")
}