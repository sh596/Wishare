package com.example.myapplication.screen.main

import androidx.lifecycle.ViewModel
import com.example.myapplication.util.roomDB.Wish
import com.example.myapplication.util.roomDB.WishDao
import java.util.Random

class MainActivityViewModel(private val wishDao: WishDao) : ViewModel() {
    val repository:TodayWishRepositoryInterface = TodayWishRepository(wishDao)

    suspend fun getWishElse() :Array<Wish> {
        return repository.getWishElse()
    }
    suspend fun getAlreadyWishList() : Array<Wish> {
        return repository.getAlreadyWishList()
    } //Room에 있는 모든 소원을 다 가져온다
    //자그만한 이스터에그
    suspend fun getTodayWish() : Wish{
        val maxCount = repository.getWishCount()
        val alreadyGetWishList = repository.getAlreadyWishList()

        val choiceWish = Wish(wishContent=choiceWish(maxCount, alreadyGetWishList))
        repository.saveGotWish(choiceWish)

        return choiceWish
    }//오늘의 소원을 하나 뽑고 저장한다.

    suspend fun choiceWish(maxCount : Int, wishList : Array<Wish>) : String{
        val currentWishIndex = Random().nextInt(maxCount)
        var currentWish : String = repository.getWish(currentWishIndex).toString()

        if(wishList.any { it.wishContent == currentWish }){
            currentWish = choiceWish(maxCount, wishList)
        }

        return currentWish
    } //오늘의 소원을 뽑는다.
}