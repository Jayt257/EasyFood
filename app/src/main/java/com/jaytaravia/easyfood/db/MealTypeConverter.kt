package com.jaytaravia.easyfood.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {

    //Room use this function to insert data into the table
    @TypeConverter
    fun fromAnyToString(attribute:Any?) : String{
        if (attribute == null)
            return ""
        return attribute as String
    }

    //Room use this function to retrieve data from database
    @TypeConverter
    fun fromStringToAny(attribute: String?):Any{
        if (attribute == null)
            return ""
        return attribute
    }


}