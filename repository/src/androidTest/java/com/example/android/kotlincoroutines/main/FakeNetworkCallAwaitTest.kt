package com.example.android.kotlincoroutines.main

import com.example.android.kotlincoroutines.main.fakes.makeFailureCall
import com.example.android.kotlincoroutines.main.fakes.makeSuccessCall
import com.example.android.kotlincoroutines.util.FakeNetworkException
import com.google.common.truth.Truth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FakeNetworkCallAwaitTest {

    @Test
    fun whenFakeNetworkCallSuccess_resumeWithResult() {
        val subject = makeSuccessCall("the title")

        // TODO: Implement test for await success
        runBlocking {
            Truth.assertThat(subject.await()).isEqualTo("the title")
        }
    }

    @Test(expected = FakeNetworkException::class)
    fun whenFakeNetworkCallFailure_throws() {
        val subject = makeFailureCall(FakeNetworkException("the error"))

        // TODO: Implement test for await failure

        // 开启异步
        GlobalScope.launch {
            subject.await()
        }

        // 阻塞线程
        runBlocking {
            subject.await()
        }
    }
}
