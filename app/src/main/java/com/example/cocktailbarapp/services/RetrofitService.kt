package com.example.cocktailbarapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitService {

    const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    const val MEAL_URL = "https://www.themealdb.com/api/json/v1/1/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)}

    val mealApi: MealApi by lazy {
        Retrofit.Builder()
            .baseUrl(MEAL_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)
    }




}