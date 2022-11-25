package com.example.cocktailbarapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktailbarapp.db.DrinkDb

class HomeViewModelFactory(
    private val drinkDb: DrinkDb
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(drinkDb) as T
    }


}