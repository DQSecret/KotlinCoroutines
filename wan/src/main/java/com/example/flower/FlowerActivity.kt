package com.example.flower

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wan.R
import com.example.wan.databinding.LayoutFlowerActBinding
import kotlinx.android.synthetic.main.layout_flower_act.*

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

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<LayoutFlowerActBinding>(this, R.layout.layout_flower_act)

        setupContentView()
        setupActionBar()
        setupNavigationMenu()
    }

    private fun setupContentView() {
        navController = findNavController(R.id.garden_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupNavigationMenu() {
        navigation_view.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}