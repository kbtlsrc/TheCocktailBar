package com.example.cocktailbarapp.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailbarapp.model.*
import com.example.cocktailbarapp.services.RetrofitService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMutableList = MutableLiveData<Drink>()
    private var popularItemMutableList =MutableLiveData<List<CategoryItems>>()
    private var mealCategoryMutableList = MutableLiveData<List<MealCategory>>()
  
    fun getRandomDrink(){

        RetrofitService.api.getRandomDrink().enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if(response.body() != null){
                    val randomDrink: Drink = response.body()!!.drinks[0]
                    Log.d("DRINK", "NAME ${randomDrink.strDrink}  id ${randomDrink.idDrink}")
                    randomMutableList.value =randomDrink

                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                Log.d("DOES NOT WORK", t.message.toString())
            }

        })
    }


    fun getPopularCategory(){
        RetrofitService.api.getPopularCategory("Cocktail").enqueue(object: Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if(response.body() != null){
                    popularItemMutableList.value = response.body()!!.drinks

                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("Home ViewModel", t.message.toString())
            }

        })
    }
    fun getMealCategory(){
        RetrofitService.mealApi.getMealCategory().enqueue(object: Callback<MealCategoryList>{
            override fun onResponse(call: Call<MealCategoryList>, response: Response<MealCategoryList>) {
               response.body()?.let {
                   mealCategoryList ->
                   mealCategoryMutableList.postValue(mealCategoryList.categories)
               }
            }

            override fun onFailure(call: Call<MealCategoryList>, t: Throwable) {
                Log.d("Home ViewModel", t.message.toString())
            }

        } )
    }




    fun observeRandomMutableList(): LiveData<Drink>{
        return randomMutableList

    }

    fun observePopularItemMutableList():LiveData<List<CategoryItems>>{
        return popularItemMutableList
    }
    fun observeMealCategoryMutableList():LiveData<List<MealCategory>>{
        return mealCategoryMutableList
    }


}