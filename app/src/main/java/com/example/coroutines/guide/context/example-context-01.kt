package com.example.coroutines.guide.context

import kotlinx.coroutines.*

/**
 * 协程上下文与调度器
 * @author DQDana
 * @since 2019-04-22 14:58
 */

@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {

    // 运行在父协程的上下文中，即 runBlocking 主协程
    launch {
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }

    // 不受限的 - 将工作在主线程中
    launch(Dispatchers.Unconfined) {
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }

    // 将会获取默认调度器
    launch(Dispatchers.Default) {
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }

    // 将使该协程获得它自己的新线程
    launch(newSingleThreadContext("MyOwnThread")) {
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
    // newSingleThreadContext为协程运行创建一个新线程。
    // 专用线程是一种非常昂贵的资源。
    // 在实际应用程序中，它必须在不再需要时释放，使用close 函数，或者存储在顶级变量中并在整个应用程序中重用。

}