package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flower.FlowerActivity
import com.example.wan.R
import com.example.wan.WanActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSunFlower()
        setupWanAndroid()
    }

    private fun setupSunFlower() {
        btn_sun_flower.setOnClickListener {
            FlowerActivity.start(this)
        }
    }

    private fun setupWanAndroid() {
        btn_wan_android.setOnClickListener {
            WanActivity.start(this)
        }
    }
}