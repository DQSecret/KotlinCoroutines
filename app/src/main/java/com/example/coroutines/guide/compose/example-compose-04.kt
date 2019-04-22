package com.example.coroutines.guide.compose

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * async 风格的函数
 * @author DQDana
 * @since 2019/4/17 7:39 PM
 *
 * 这种带有异步函数的编程风格仅供参考，因为这在其它编程语言中是一种受欢迎的风格。
 * 在 Kotlin 的协程中使用这种风格是强烈不推荐的，原因如下所述。
 *
 * 考虑一下如果 val one = somethingUsefulOneAsync() 这一行和 one.await() 表达式这里在代码中有逻辑错误，
 * 并且程序抛出了异常以及程序在操作的过程中被中止，将会发生什么。
 * 通常情况下，一个全局的异常处理者会捕获这个异常，将异常打印成日记并报告给开发者，
 * 但是反之该程序将会继续执行其它操作。
 * 但是这里我们的 somethingUsefulOneAsync 仍然在后台执行，尽管如此，启动它的那次操作也会被终止。
 * 这个程序将不会进行结构化并发，如下一小节所示。
 */

fun main() {
  val time = measureTimeMillis {
    // 我们可以在协程外面启动异步执行: 此处例如 api 请求
    val one = somethingUsefulOneAsync()
    val two = somethingUsefulTwoAsync()
    // but waiting for a result must involve either suspending or blocking.
    // here we use `runBlocking { ... }` to block the main thread while waiting for the result
    runBlocking {
      println("The answer is ${one.await() + two.await()}")
    }
  }
  println("Completed in $time ms")
}

/**
 * 注意，这些 xxxAsync 函数不是 挂起 函数。
 * 它们可以在任何地方被使用。
 * 然而，它们总是在调用它们的代码中意味着异步（这里的意思是 并发）执行。
 *
 * @return : Deferred<Int>
 */
fun somethingUsefulOneAsync() = GlobalScope.async {
  doSomethingUsefulOne()
}

fun somethingUsefulTwoAsync() = GlobalScope.async {
  doSomethingUsefulTwo()
}