package com.example.cocktailbarapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailbarapp.db.DrinkDb
import com.example.cocktailbarapp.model.drink.CocktailList
import com.example.cocktailbarapp.model.drink.Drink
import com.example.cocktailbarapp.services.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkDetailViewModel(
    val drinkDb: DrinkDb
): ViewModel() {

    private var detailMutableList = MutableLiveData<Drink>()

    fun getDrinkDetails(id : String){
        RetrofitService.api.getDrinkDetail(id).enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {

                if(response.body() != null){

                    detailMutableList.value = response.body()!!.drinks[0]
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

    fun observeDrinkDetail(): LiveData<Drink> {
        return detailMutableList
    }

    fun insertDrink(drink: Drink){
        viewModelScope.launch {
            drinkDb.drinkDao().insert(drink)


        }
    }


}