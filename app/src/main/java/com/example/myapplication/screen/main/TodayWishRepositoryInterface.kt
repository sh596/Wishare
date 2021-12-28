package com.example.myapplication.screen.main

import com.example.myapplication.util.roomDB.Wish

interface TodayWishRepositoryInterface {
    suspend fun getWishCount() : Int //Firebase에 소원 개수를 가져온다.
    suspend fun getAlreadyWishList() : Array<Wish> //Room에 저장된 모든 소원을 다 가져온다.
    suspend fun getWishElse() : Array<Wish> //Room에서 남이 작성한 모든 소원을 가져온다.
    suspend fun getWish(index : Int) : String? //Firebase에서 한개만 가져온다. 이때 중법 제거
    suspend fun getAllWish() : List<String> //Firebase에서 전체를 가져온다.
    suspend fun saveGotWish(wish : Wish) //저장
}