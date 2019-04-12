package com.example.coroutines.guide.cancel

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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
      repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
      }
    } finally {

      // 睡眠(行为), 先去休息, 某时某刻回来工作, 自动的
      // 挂起(行为), 先去休息, 需要你工作的时候, 我会主动叫你的(被动的)
      // 阻塞, 由于CPU资源的缺失, 暂停工作, 等待分配资源, 获得后会自动恢复工作

      // 然而，在真实的案例中，当你需要挂起一个被取消的协程
      // eg: 不知道有什么场景 = =
      withContext(NonCancellable) {
        println("I'm running finally")
        delay(1000L)
        println("And I've just delayed for 1 sec because I'm non-cancellable")
      }
    }
  }
  delay(1300L) // 延迟一段时间
  println("main: I'm tired of waiting!")
  job.cancelAndJoin() // 取消该任务并等待它结束
  println("main: Now I can quit.")
}