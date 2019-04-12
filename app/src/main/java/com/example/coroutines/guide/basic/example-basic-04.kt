package com.example.coroutines.guide.basic

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程测试代码
 * @author DQDana
 * @since 2019/4/11 3:14 PM
 */

fun main() = runBlocking {

  /*
  除了由不同的构建器提供协程作用域之外，还可以使用 coroutineScope 构建器声明自己的作用域。
  它会创建新的协程作用域并且在所有已启动子协程执行完毕之前不会结束。
  runBlocking 与 coroutineScope 的主要区别在于后者在等待所有子协程执行完毕时不会阻塞当前线程。
   */

  launch {
    // 4
    delay(200L)
    println("Task from runBlocking")
  }

  coroutineScope {
    // 2 Creates a new coroutine scope (协程范围)
    launch {
      delay(500L)
      println("Task from nested(嵌套) launch")
    }

    // 1
    delay(100L)
    println("Task from coroutine scope")
  }

  // 3
  println("Coroutine scope is over")
}