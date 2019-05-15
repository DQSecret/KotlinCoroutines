package com.example.coroutines

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.example.UserService
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var job: Job
    private val userService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()

        btn_login.setOnClickListener {
            doLogin("DQ", "123456")
        }

        setupFrameAnim()
        setupCoroutine()
    }

    private fun doLogin(username: String, password: String) {
        launch {
            val time = measureTimeMillis {
                progress.visibility = View.VISIBLE

                val user = withContext(Dispatchers.IO) { userService.doLogin(username, password) }
                val currentFriends = async(Dispatchers.IO) { userService.requestCurrentFriends(user) }
                val suggestedFriends = async(Dispatchers.IO) { userService.requestSuggestedFriends(user) }

                val finalUser = user.copy(friends = currentFriends.await() + suggestedFriends.await())
                val msg = "User ${finalUser.name} has ${finalUser.friends.size} friends"
                toast(msg)
                Logger.d(msg)
                progress.visibility = View.GONE
            }
            Logger.d("doLogin() 耗时: $time")
        }
    }

    private fun setupCoroutine() {
        tv_hi.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    delay(2_000)
                }
                tv_hi.text = "来自协程的文本"
            }
        }
    }

    private fun setupFrameAnim() {
        // 灯
        frame_anim_deng.setBackgroundResource(R.drawable.frame_animation_deng)
        val frameDeng = frame_anim_deng.background as AnimationDrawable
        frameDeng.start()

        // 骰子
        frame_anim_shai_zi.setBackgroundResource(R.drawable.frame_animation_shai_zi)
        val frameShaiZi = frame_anim_shai_zi.background as AnimationDrawable
        frameShaiZi.start()
    }

    override fun onStart() {
        super.onStart()
        blurLayout.startBlur()
        blurLayout.lockView()
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
        blurLayout.pauseBlur()
    }
}