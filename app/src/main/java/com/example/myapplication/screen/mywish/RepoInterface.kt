package com.example.myapplication.screen.mywish

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.util.roomDB.Wish

interface RepoInterface {
    fun getMyWishList() : ArrayList<Wish>
}