package com.example.cocktailbarapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktailbarapp.model.CocktailList
import com.example.cocktailbarapp.model.Drink
import com.example.cocktailbarapp.services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DrinkDetailViewModel: ViewModel() {

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
}