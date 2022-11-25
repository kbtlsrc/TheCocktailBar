package com.example.cocktailbarapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters


@TypeConverters
class DrinkTypeConverter {

    @TypeConverter
    fun fromAnyToString(attribute: Any?) : String{
        if(attribute == null){
            return ""
        }
        return attribute as String
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?) :Any{
        if (attribute == null)
            return ""
        return attribute
    }


}