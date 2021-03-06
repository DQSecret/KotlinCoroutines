package com.example.coroutines.guide.context

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 子协程
 * @author DQDana
 * @since 2019-04-22 18:33
 */

////////////////////////////////////////////////////////////////////////////////////////////////////
/// 当一个协程被其它协程在 CoroutineScope 中启动的时候，
/// 它将通过 CoroutineScope.coroutineContext 来承袭上下文，
/// 并且这个新协程的 Job 将会成为父协程任务的 子 任务。
///
/// 当一个父协程被取消的时候，所有它的子协程也会被递归的取消。
//
/// 然而，当 GlobalScope 被用来启动一个协程时，它与作用域无关且是独立被启动的。
////////////////////////////////////////////////////////////////////////////////////////////////////

fun main() = runBlocking {

    // 启动一个携程来处理某种传入请求(request)
    val request = launch {

        // 孵化了两个子任务, 其中一个通过 GlobalScope 启动
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }

        // 另一个则承袭了父协程的上下文
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }

    delay(500)
    request.cancel() // 取消请求（request）的执行
    delay(1000) // 延迟一秒钟来看看发生了什么
    println("main: Who has survived request cancellation?")
}