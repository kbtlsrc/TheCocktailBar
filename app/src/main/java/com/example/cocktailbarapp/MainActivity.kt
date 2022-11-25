package com.example.cocktailbarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.cocktailbarapp.R
import com.example.cocktailbarapp.db.DrinkDb
import com.example.cocktailbarapp.viewmodel.HomeViewModel
import com.example.cocktailbarapp.viewmodel.HomeViewModelFactory


import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val viewModel : HomeViewModel by lazy {
        val drinkDb = DrinkDb.getInstance(this)
        val homeViewModelFactory = HomeViewModelFactory(drinkDb)
        ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
    }
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