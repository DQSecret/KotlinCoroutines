package com.example.coroutines.guide.cancel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程的取消是 协作 的。一段协程代码必须协作才能被取消。
 * 所有 kotlinx.coroutines 中的挂起函数都是 可被取消的。
 * 它们检查协程的取消，并在取消时抛出 CancellationException。
 * 然而，如果协程正在执行计算任务，并且没有检查取消的话，那么它是不能被取消的
 *
 * @author DQDana
 * @since 2019/4/12 5:23 PM
 */

fun main() = runBlocking {

  val startTime = System.currentTimeMillis()

  val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
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