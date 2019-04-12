package com.example.coroutines.guide.cancel

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 * 超时
 * @author DQDana
 * @since 2019/4/12 6:16 PM
 */

fun main() = runBlocking {
  // 在实践中绝大多数取消一个协程的理由是它有可能超时。
  // 当你手动追踪一个相关 Job 的引用并启动了一个单独的协程在延迟后取消追踪，
  // 这里已经准备好使用 withTimeout 函数来做这件事。 来看看示例代码：

  withTimeout(1_300L) {
    repeat(1000) {
      println("I'm sleeping $it ...")
      delay(500L)
    }
  }
}