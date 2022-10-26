package com.flexath.celluloid.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.flexath.celluloid.R.layout.activity_main)

        val btmNgv = findViewById<BottomNavigationView>(com.flexath.celluloid.R.id.bottomNvgView)
        val navHost = supportFragmentManager.findFragmentById(com.flexath.celluloid.R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController

        btmNgv.setupWithNavController(navController)
    }

}