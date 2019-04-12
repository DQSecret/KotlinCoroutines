package com.example.coroutines.guide.cancel

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 取消协程的执行
 * @author DQDana
 * @since 2019/4/12 1:35 PM
 */

fun main() = runBlocking {

  val job = launch {
    repeat(1_0) {
      println("I'm sleeping $it ...")
      delay(500L)
    }
  }

  delay(1300L)
  println("main: I'm tired of waiting!")
  job.cancel()
  // 这里 join, 并不是开启协程的意思, 而是阻塞主线程, 直到协程执行完毕的意思
  // 因为, 协程被 cancel 后, 会自动跑出一个 CancellationException
  // 在 Finally{} 中, 可以执行扫尾工作
  // 故 需要等待协程执行完毕, 才可以结束主线程
  // 如果, 无需扫尾工作,
  job.join()

  // job.cancelAndJoin()
  println("main: Now i can quit")
}