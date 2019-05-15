package com.example.coroutines

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFrameAnim()
        setupCoroutine()
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
        super.onStop()
        blurLayout.pauseBlur()
    }
}