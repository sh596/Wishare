package com.example.myapplication.util.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WishDao {
    @Query("SELECT * FROM wishs")
    fun getAll() : Array<Wish>

    @Query("SELECT * FROM wishs WHERE mine = :mine")
    fun getMineWish(mine : Int) : Array<Wish>

    @Insert
    fun insert(vararg wish: Wish)

    @Delete
    fun delete(wish: Wish)
}