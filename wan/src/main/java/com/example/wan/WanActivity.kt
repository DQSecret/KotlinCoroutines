package com.example.wan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * WanAndroid
 * @author DQDana
 * @since 2019-05-16 16:28
 */
class WanActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            Intent(context, WanActivity::class.java).let {
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_wan_act)
    }
}