package com.example.cocktailbarapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cocktailbarapp.model.drink.Drink


@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drink: Drink)

    @Delete
    suspend fun delete(drink: Drink)

    @Query("SELECT * FROM drinkInformation")
    fun getDrink(): LiveData<List<Drink>>

}