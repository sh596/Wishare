package com.example.myapplication.screen.mywish

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyWishViewModel : ViewModel() {
    private val repo : RepoInterface = MyWishRepo()
    val myWishList = MutableLiveData<ArrayList<String>>()

    init {
        myWishList.value = repo.getMyWishList()
    }
}