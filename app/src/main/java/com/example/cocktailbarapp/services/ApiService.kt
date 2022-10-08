package com.example.cocktailbarapp.services

import com.example.cocktailbarapp.model.CategoryList
import com.example.cocktailbarapp.model.CocktailList
import com.example.cocktailbarapp.model.MealCategory
import com.example.cocktailbarapp.model.MealCategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random.php")
    fun getRandomDrink(): Call<CocktailList>

    @GET("lookup.php")
    fun getDrinkDetail(@Query("i") id: String): Call<CocktailList>

    @GET("filter.php?")
    fun getPopularCategory(@Query("c") category:String): Call<CategoryList>




}