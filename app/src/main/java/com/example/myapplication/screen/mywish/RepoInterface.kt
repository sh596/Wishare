package com.example.myapplication.screen.mywish

import androidx.lifecycle.MutableLiveData

interface RepoInterface {
    fun getMyWishList() : ArrayList<String>
}