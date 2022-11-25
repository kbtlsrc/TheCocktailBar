package com.example.cocktailbarapp.services

import com.example.cocktailbarapp.model.drink.CategoryList
import com.example.cocktailbarapp.model.drink.CocktailList
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