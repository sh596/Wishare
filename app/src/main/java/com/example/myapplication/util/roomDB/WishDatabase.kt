package com.example.myapplication.util.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wish::class], version = 1)
abstract class WishDatabase : RoomDatabase() {
    abstract fun wishDao() : WishDao
    companion object {
        @Volatile
        private var INSTANCE : WishDatabase? = null
        fun getInstance(context: Context) : WishDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    WishDatabase::class.java, "wish_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}