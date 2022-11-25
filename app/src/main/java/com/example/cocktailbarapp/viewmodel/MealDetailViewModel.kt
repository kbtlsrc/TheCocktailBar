package com.example.cocktailbarapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailbarapp.model.meal.AllMealList
import com.example.cocktailbarapp.model.meal.Meal
import com.example.cocktailbarapp.services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealDetailViewModel: ViewModel() {
private val mealDetailMutableList =MutableLiveData<List<Meal>>()
    fun getMealDetailCategory(mealDetailCategoryName: String){
        RetrofitService.mealApi.getMealDetailCategory(mealDetailCategoryName).enqueue(object:
            Callback<AllMealList> {
            override fun onResponse(
                call: Call<AllMealList>,
                response: Response<AllMealList>
            ) {
                response.body()?.let {
                    mealDetailCategoryList ->
                    mealDetailMutableList.postValue(mealDetailCategoryList.meals)
                }
            }

            override fun onFailure(call: Call<AllMealList>, t: Throwable) {
               Log.d("Error on mealdetail", t.message.toString())
            }

        })

    }

    fun observeMealDetailMutableList(): LiveData<List<Meal>>{
        return mealDetailMutableList
    }
}