package com.example.coroutines.guide.context

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 在不同线程中, 跳跃, 改变协程的上下文
 * @author DQDana
 * @since 2019-04-22 15:52
 */

@ObsoleteCoroutinesApi
fun main() {
    newSingleThreadContext("Ctx1").use { ctx1 ->

        newSingleThreadContext("Ctx2").use { ctx2 ->

            runBlocking(ctx1) {
                log("Started in ctx1")

                withContext(ctx2) {
                    log("Working in ctx2")
                }

                log("Back to ctx1")
            }
        }
    }
}