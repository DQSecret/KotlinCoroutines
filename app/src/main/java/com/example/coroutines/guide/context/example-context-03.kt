package com.example.coroutines.guide.context

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * 调试协程与线程
 * @author DQDana
 * @since 2019-04-22 15:42
 */

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
}