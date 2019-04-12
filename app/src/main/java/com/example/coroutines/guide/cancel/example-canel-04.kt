package com.example.coroutines.guide.cancel

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 被取消时抛出 CancellationException
 * 可以使用 try-finally 处理 or Kotlin.use()
 *
 * @author DQDana
 * @since 2019/4/12 5:36 PM
 */

fun main() = runBlocking {

  val job = launch {
    try {
      repeat(1_000) {
        println("I'm sleeping $it ...")
        delay(500L)
      }
    } finally {
      println("I'm running finally")
    }
  }

  delay(1300L)
  println("main: I'm tired of waiting!")
  job.cancelAndJoin()
  println("main: Now I can quit.")

}