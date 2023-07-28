package com.jaytaravia.easyfood.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jaytaravia.easyfood.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase(){

    abstract fun mealDao():MealDao

    companion object{
        @Volatile // Any change in this INSTANCE from any thread will be visible to any other thread
        var INSTANCE: MealDatabase? = null

        @Synchronized // only one thread can have instance from this room database
        fun getInstance(context: Context) : MealDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }


    }
}