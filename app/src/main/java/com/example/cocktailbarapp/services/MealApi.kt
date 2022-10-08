package com.example.cocktailbarapp.services

import com.example.cocktailbarapp.model.AllMealList
import com.example.cocktailbarapp.model.MealCategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("categories.php")
    fun getMealCategory(): Call<MealCategoryList>

    @GET("filter.php")
    fun getMealDetailCategory(@Query ("c") mealName: String): Call<AllMealList>
}