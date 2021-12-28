package com.example.myapplication.screen.main

import androidx.lifecycle.ViewModel
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDao
import java.util.Random

class MainActivityViewModel(private val wishDao: WishDao) : ViewModel() {
    val repository = TodayWishRepository(wishDao)

    suspend fun getTodayWish() : String{
        val maxCount = repository.getWishCount()
        val wishList = repository.getAlreadyWishList()

        return choiceWish(maxCount, wishList)
    }

    suspend fun choiceWish(maxCount : Int, wishList : Array<Wish>) : String{
        val currentWishIndex = Random().nextInt(maxCount + 1)
        var currentWish : String = repository.getWish(currentWishIndex)

        if(currentWish == null || wishList.any { it.wishContent == currentWish }){
            currentWish = choiceWish(maxCount, wishList)
        }

        return currentWish
    }
}