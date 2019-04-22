package com.example.coroutines.guide.context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 非受限调度器 vs 受限调度器
 * Unconfined vs confined dispatcher
 *
 * @author DQDana
 * @since 2019-04-22 15:29
 */

fun main() = runBlocking<Unit> {

    // 非受限 任何地方(线程)执行,
    // 非受限调度器适合协程不消耗 CPU 时间也不更新任何限于特定线程的共享数据（如 UI）的情境。
    launch(Dispatchers.Unconfined) {
        // 非受限的——将和主线程一起工作
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    // ⬆️因此，该协程从 runBlocking {……} 协程中承袭了上下文并在主线程中执行，
    // 同时使用非受限调度器的协程从被执行 delay 函数的默认执行者线程中恢复。


    // 另一方面，协程调度器默认承袭外部的 CoroutineScope 的调度器。
    // 特别是 runBlocking 的默认协程调度器仅限于调用者线程，
    // 因此承袭它将会把执行限制在该线程中， 并具有可预测的 FIFO 调度的效果。
    launch {
        // 父协程的上下文，主 runBlocking 协程
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }

}