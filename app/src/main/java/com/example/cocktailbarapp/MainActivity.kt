package com.example.cocktailbarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.cocktailbarapp.R


import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController by lazy {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.main_fragment) as NavHostFragment

            navHostFragment.navController
        }


        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }
}