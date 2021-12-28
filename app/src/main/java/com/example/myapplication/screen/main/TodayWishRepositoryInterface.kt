package com.example.myapplication.screen.main

import com.example.myapplication.util.roomDB.Wish

interface TodayWishRepositoryInterface {
    suspend fun getWishCount() : Int

    suspend fun getAlreadyWishList() : Array<Wish>
    suspend fun getWish(index : Int) : String
    suspend fun getAllWish() : List<String>
}