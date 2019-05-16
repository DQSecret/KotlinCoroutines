package com.example.flower

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wan.R

/**
 * SunFlower
 * @author DQDana
 * @since 2019-05-16 16:29
 */
class FlowerActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            Intent(context, FlowerActivity::class.java).let {
                context.startActivity(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_flower_act)
    }
}