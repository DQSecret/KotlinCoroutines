package com.example.coroutines.guide.cancel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 使计算代码可取消
 * 第一种方法是定期调用挂起函数来检查取消。对于这种目的 yield 是一个好的选择。
 * 另一种方法是显式的检查取消状态。
 * 让我们试试第二种方法。
 * 将前一个示例中的 while (i < 5) 替换为 while (isActive) 并重新运行它。
 *
 * @author DQDana
 * @since 2019/4/12 5:23 PM
 */

fun main() = runBlocking {

  val startTime = System.currentTimeMillis()

  val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (isActive) { // 一个执行计算的循环，只是为了占用 CPU
      if (System.currentTimeMillis() >= nextPrintTime) {
        println("I'm sleeping ${i++} ...")
        nextPrintTime += 500L
      }
    }
  }

  delay(1_300L)
  println("main: I'm tired of waiting!")
  job.cancelAndJoin() // cancels the job and waits for its completion
  println("main: Now I can quit.")
}