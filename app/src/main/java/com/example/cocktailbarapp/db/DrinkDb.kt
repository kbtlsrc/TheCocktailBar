package com.example.cocktailbarapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cocktailbarapp.model.drink.Drink

@Database(entities = [Drink::class], version = 1)
@TypeConverters(DrinkTypeConverter::class)
 abstract class DrinkDb: RoomDatabase() {

     abstract fun drinkDao(): DrinkDao

     companion object{
         @Volatile
         var INSTANCE: DrinkDb? = null

         @Synchronized
         fun getInstance(context: Context): DrinkDb{
             if (INSTANCE == null){
                 INSTANCE = Room.databaseBuilder(context, DrinkDb::class.java, "drink.db")
                     .fallbackToDestructiveMigration()
                     .build()
             }
             return INSTANCE as DrinkDb
         }
     }
}